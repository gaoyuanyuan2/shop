package com.yan.shop.dto;

import java.util.Date;

/**
 * Created by yan on  30/07/2019.
 */
public class OrderChartReq {

    private Integer countType;

    private Date startTime;

    private Date endTime;

    private Integer type;

    public Integer getCountType() {
        return countType;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
