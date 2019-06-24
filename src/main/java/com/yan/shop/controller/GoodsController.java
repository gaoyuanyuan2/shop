package com.yan.shop.controller;

import com.yan.shop.dto.GoodsListReq;
import com.yan.shop.entity.Goods;
import com.yan.shop.response.PageDataResult;
import com.yan.shop.service.GoodsService;
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
@RequestMapping("goods")
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/goodsManage")
    public String toPage() {
        logger.info("进入goods管理");
        return "goods/goodsManage";
    }

    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult getGoodsList(HttpServletRequest request, GoodsListReq req) {

        PageDataResult pdr = new PageDataResult();
        try {
            if (null == req.getPage()) {
                req.setPage(1);
            }
            if (null == req.getLimit()) {
                req.setLimit(10);
            }
            // 获取角色列表
            pdr = goodsService.getGoodList(req);
            logger.info("商品列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("商品列表查询异常！", e);
        }
        return pdr;
    }


    @PostMapping("/setGoods")
    @ResponseBody
    public Map<String, Object> setRole(Goods goods) {
        logger.info("设置Goods[新增或更新]！goods:" + goods);
        Map<String, Object> data;
        if (goods.getId() == null) {
            //新增角色
            data = goodsService.addGoods(goods);
        } else {
            //修改角色
            data = goodsService.updateGoods(goods);
        }
        return data;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteGoods(@RequestParam("id") Long id) {

        return goodsService.deleteGoods(id);


    }
}
