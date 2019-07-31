package com.yan.shop.service;

import com.yan.shop.dto.OrderChartReq;
import com.yan.shop.dto.OrderChartResp;

/**
 * Created by yan on  30/07/2019.
 */
public interface ChartsService {
    OrderChartResp orderStatistics(OrderChartReq req);
}
