package com.yan.shop.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yan.shop.common.utils.ShopUtils;
import com.yan.shop.dao.OrderMapper;
import com.yan.shop.dto.OrderListReq;
import com.yan.shop.entity.Order;
import com.yan.shop.response.PageDataResult;
import com.yan.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yan on  19/06/2019.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public PageDataResult getOrderList(OrderListReq req) {
        PageDataResult pageDataResult = new PageDataResult();
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Order> order = orderMapper.selectBy(buildOrderCondition(req));
        if (!CollectionUtils.isEmpty(order)) {
            pageDataResult.setList(order);
            pageDataResult.setTotals(Integer.valueOf(page.getTotal() + ""));
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> addOrder(Order order) {
        Map<String, Object> data = new HashMap();
        try {
            order.setCreateTime(new Timestamp(System.currentTimeMillis()));
            int result = orderMapper.insert(order);
            if (buildReturn(data, result)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> updateOrder(Order order) {
        Map<String, Object> data = new HashMap();
        try {
            order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            int result = orderMapper.updateByPrimaryKey(order);
            if (buildReturn(data, result)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> deleteOrder(Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            if (ShopUtils.buildRes(data, orderMapper.deleteByPrimaryKey(id), logger, id)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除异常！", e);
        }
        return data;
    }

    private boolean buildReturn(Map<String, Object> data, int result) {
        if (result == 0) {
            data.put("code", 0);
            data.put("msg", "失败");
            logger.error("失败");
            return true;
        }
        data.put("code", 1);
        data.put("msg", "成功!");
        return false;
    }

    private Order buildOrderCondition(OrderListReq req) {
        Order order = new Order();
        order.setStartName(req.getStartName());
        order.setType(req.getType() == null ? 0 : req.getType());
        order.setGiveGood(req.getGiveGood() == null ? 0 : req.getGiveGood());
        order.setHavePicture(req.getHavePicture() == null ? 0 : req.getHavePicture());
        order.setPhone(req.getPhone());
        return order;
    }
}
