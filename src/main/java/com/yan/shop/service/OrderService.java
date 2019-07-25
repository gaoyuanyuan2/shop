package com.yan.shop.service;

import com.yan.shop.dto.OrderListReq;
import com.yan.shop.entity.Order;
import com.yan.shop.response.PageDataResult;

import java.util.Map;

/**
 * Created by yan on  19/06/2019.
 */
public interface OrderService {
    PageDataResult getOrderList(OrderListReq req);

    Map<String, Object> addOrder(Order order);

    Map<String, Object> updateOrder(Order order);

    Map<String, Object> deleteOrder(Long id);

}
