package com.yan.shop.service.impl;

import com.yan.shop.dao.OrderMapper;
import com.yan.shop.dto.ChartSeries;
import com.yan.shop.dto.OrderChartReq;
import com.yan.shop.dto.OrderChartResp;
import com.yan.shop.entity.Order;
import com.yan.shop.model.SearchTypeEnum;
import com.yan.shop.service.ChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yan on  30/07/2019.
 */
@Service
public class ChartsServiceImpl implements ChartsService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderChartResp orderStatistics(OrderChartReq req) {
        List<Order> orderList = orderMapper.countBy(SearchTypeEnum.Holder.parse(req.getCountType()).getDesc(),
                req.getType(), req.getStartTime(), req.getEndTime());
        OrderChartResp orderChartResp = new OrderChartResp();
        String[] legends = {"返现额", "利润", "成本价","销售额"};
        ChartSeries salesSeries = new ChartSeries("销售额", "line", "销售额");
        ChartSeries costSeries = new ChartSeries("成本价", "line", "成本价");
        ChartSeries returnSeries = new ChartSeries("返现额", "line", "返现额");
        ChartSeries profitsSeries = new ChartSeries("利润", "line", "利润");
        List<String> dateTimes = orderList.stream().map(Order::getDateTime).collect(Collectors.toList());
        Map<String, Double> salesMap = orderList.stream().collect(Collectors.toMap(Order::getDateTime,
                Order::getSalePrice, (key1, key2) -> key2));
        salesSeries.setData(getSeriesData(dateTimes, salesMap));
        Map<String, Double> costMap = orderList.stream().collect(Collectors.toMap(Order::getDateTime,
                Order::getCostPrice, (key1, key2) -> key2));
        costSeries.setData(getSeriesData(dateTimes, costMap));
        Map<String, Double> returnMap = orderList.stream().collect(Collectors.toMap(Order::getDateTime,
                Order::getReturnCash, (key1, key2) -> key2));
        returnSeries.setData(getSeriesData(dateTimes, returnMap));
        Map<String, Double> profitsSeriesMap = orderList.stream().collect(Collectors.toMap(Order::getDateTime,
                Order::getProfits, (key1, key2) -> key2));
        profitsSeries.setData(getSeriesData(dateTimes, profitsSeriesMap));
        ChartSeries[] chartSeriesArray = { returnSeries, profitsSeries, costSeries,salesSeries};
        orderChartResp.setLegend(legends);
        orderChartResp.setxAxis(getXAxis(dateTimes));
        orderChartResp.setSeries(chartSeriesArray);
        return orderChartResp;
    }

    private String[] getXAxis(List<String> dateTimes) {
        String[] dateTimesTem = new String[dateTimes.size()];
        return dateTimes.toArray(dateTimesTem);
    }

    private Double[] getSeriesData(List<String> dateTimes, Map<String, Double> map) {
        Double[] seriesData = new Double[dateTimes.size()];
        if (CollectionUtils.isEmpty(map)) {
            return seriesData;
        }
        for (int i = 0; i < dateTimes.size(); i++) {
            String date = dateTimes.get(i);
            Double count = map.get(date);
            if (count != null) {
                seriesData[i] = count;
            } else {
                seriesData[i] = 0D;
            }
        }
        return seriesData;
    }
}
