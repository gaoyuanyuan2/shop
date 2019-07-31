package com.yan.shop.controller;

import com.yan.shop.dto.OrderChartReq;
import com.yan.shop.dto.OrderChartResp;
import com.yan.shop.service.ChartsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yan on  30/07/2019.
 */
@Controller
@RequestMapping("charts")
public class ChartsController {

    @Autowired
    private ChartsService chartsService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/orderStatistics")
    public String toPage() {
        logger.info("进入管理");
        return "charts/orderStatistics";
    }

    @PostMapping("/order/statistics")
    @ResponseBody
    public OrderChartResp orderStatistics(OrderChartReq req) {
        OrderChartResp orderChartResp = chartsService.orderStatistics(req);
        return orderChartResp;
    }
}
