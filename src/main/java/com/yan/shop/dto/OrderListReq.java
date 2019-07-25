package com.yan.shop.dto;

/**
 * Created by yan on  25/07/2019.
 */
public class OrderListReq {

    private String startName;

    private Integer page;

    private Integer limit;

    private Integer type;

    private Integer giveGood;

    private Integer havePicture;

    private String phone;

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGiveGood() {
        return giveGood;
    }

    public void setGiveGood(Integer giveGood) {
        this.giveGood = giveGood;
    }

    public Integer getHavePicture() {
        return havePicture;
    }

    public void setHavePicture(Integer havePicture) {
        this.havePicture = havePicture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
