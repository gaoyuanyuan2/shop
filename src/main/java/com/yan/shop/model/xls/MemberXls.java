package com.yan.shop.model.xls;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Created by yan on  13/08/2019.
 */
public class MemberXls extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    private String label;

    @ExcelProperty(index = 2)
    private String phone;

    @ExcelProperty(index = 3)
    private String email;

    @ExcelProperty(index = 4)
    private Integer ordersCount;

    @ExcelProperty(index = 5)
    private Double ordersTotalPrice;

    @ExcelProperty(index = 6)
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(Integer ordersCount) {
        this.ordersCount = ordersCount;
    }

    public Double getOrdersTotalPrice() {
        return ordersTotalPrice;
    }

    public void setOrdersTotalPrice(Double ordersTotalPrice) {
        this.ordersTotalPrice = ordersTotalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
