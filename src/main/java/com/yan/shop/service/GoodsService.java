package com.yan.shop.service;

import com.yan.shop.dto.GoodsListReq;
import com.yan.shop.entity.Goods;
import com.yan.shop.response.PageDataResult;

import java.util.Map;

/**
 * Created by yan on  19/06/2019.
 */
public interface GoodsService {
    PageDataResult getGoodList(GoodsListReq req);

    Map<String, Object> addGoods(Goods goods);

    Map<String, Object> updateGoods(Goods goods);

    Map<String, Object> deleteGoods(Long id);

    Map<String, Object> addSearch(Long id);

    Map<String, Object> addSale(Long id);
}
