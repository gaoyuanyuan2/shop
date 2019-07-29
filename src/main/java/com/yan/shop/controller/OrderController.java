package com.yan.shop.controller;

import com.yan.shop.dto.OrderListReq;
import com.yan.shop.entity.Order;
import com.yan.shop.response.PageDataResult;
import com.yan.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by yan on  19/06/2019.
 */
@Controller
@RequestMapping("order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;

    @RequestMapping("/orderManage")
    public String toPage() {
        logger.info("进入order管理");
        return "order/orderManage";
    }

    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult getOrderList(HttpServletRequest request, OrderListReq req) {

        PageDataResult pdr = new PageDataResult();
        try {
            if (null == req.getPage()) {
                req.setPage(1);
            }
            if (null == req.getLimit()) {
                req.setLimit(10);
            }
            pdr = orderService.getOrderList(req);
            logger.info("列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("列表查询异常！", e);
        }
        return pdr;
    }


    @PostMapping("/setOrder")
    @ResponseBody
    public Map<String, Object> setRole(Order order) {
        logger.info("设置Order[新增或更新]！order:" + order);
        Map<String, Object> data;
        if (order.getId() == null) {
            data = orderService.addOrder(order);
        } else {
            data = orderService.updateOrder(order);
        }
        return data;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteOrder(@RequestParam("id") Long id) {

        return orderService.deleteOrder(id);


    }

}
