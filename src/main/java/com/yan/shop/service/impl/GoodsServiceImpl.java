package com.yan.shop.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yan.shop.common.utils.ShopUtils;
import com.yan.shop.dao.GoodsMapper;
import com.yan.shop.dto.GoodsListReq;
import com.yan.shop.entity.Goods;
import com.yan.shop.response.PageDataResult;
import com.yan.shop.service.GoodsService;
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
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public PageDataResult getGoodList(GoodsListReq req) {
        PageDataResult pageDataResult = new PageDataResult();
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Goods> goods = goodsMapper.selectBy(req.getSort() == null ? 0 : req.getSort(), buildGoodsCondition(req));
        if (!CollectionUtils.isEmpty(goods)) {
            pageDataResult.setList(goods);
            pageDataResult.setTotals(Integer.valueOf(page.getTotal() + ""));
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> addGoods(Goods goods) {
        Map<String, Object> data = new HashMap();
        try {
            goods.setCreateTime(new Timestamp(System.currentTimeMillis()));
            int result = goodsMapper.insert(goods);
            if (buildReturn(data, result)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加异常！", e);
            goodsMapper.addSearchTimesByNameAndType(goods.getStartName(), goods.getType());
        }
        return data;
    }

    @Override
    public Map<String, Object> updateGoods(Goods goods) {
        Map<String, Object> data = new HashMap();
        try {
            goods.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            int result = goodsMapper.updateByPrimaryKey(goods);
            if (buildReturn(data, result)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> deleteGoods(Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除用户
            if (ShopUtils.buildRes(data, goodsMapper.deleteByPrimaryKey(id), logger, id)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> addSearch(Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除用户
            if (ShopUtils.buildRes(data, goodsMapper.addSearch(id), logger, id)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("addSearch异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> addSale(Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除用户
            if (ShopUtils.buildRes(data, goodsMapper.addSale(id), logger, id)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("addSale异常！", e);
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

    private Goods buildGoodsCondition(GoodsListReq req) {
        Goods goods = new Goods();
        goods.setStartName(req.getStartName());
        goods.setType(req.getType() == null ? 0 : req.getType());
        goods.setHaveBrushSingle(req.getHaveBrushSingle() == null ? 0 : req.getHaveBrushSingle());
        goods.setDetailsOptimization(req.getDetailsOptimization() == null ? 0 : req.getDetailsOptimization());
        goods.setHaveProduct(req.getHaveProduct());
        return goods;
    }
}
