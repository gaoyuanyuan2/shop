package com.yan.shop.dto;

/**
 * Created by yan on  20/06/2019.
 */
public class GoodsListReq {

    private String startName;

    private Integer page;

    private Integer limit;

    private Integer type;

    private Integer haveBrushSingle;

    private Integer detailsOptimization;

    private Integer haveProduct;

    private Integer sort;

    public Integer getHaveProduct() {
        return haveProduct;
    }

    public void setHaveProduct(Integer haveProduct) {
        this.haveProduct = haveProduct;
    }

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

    public Integer getHaveBrushSingle() {
        return haveBrushSingle;
    }

    public void setHaveBrushSingle(Integer haveBrushSingle) {
        this.haveBrushSingle = haveBrushSingle;
    }

    public Integer getDetailsOptimization() {
        return detailsOptimization;
    }

    public void setDetailsOptimization(Integer detailsOptimization) {
        this.detailsOptimization = detailsOptimization;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
