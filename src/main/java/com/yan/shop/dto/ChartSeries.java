package com.yan.shop.dto;

import java.util.Arrays;

/**
 * Created by yan on  23/01/2019.
 */
public class ChartSeries {

    private String name;

    private String type;

    private String stack;

    private Double[] data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public Double[] getData() {
        return data;
    }

    public void setData(Double[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ChartSeries{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", stack='" + stack + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public ChartSeries(String name, String type, String stack) {
        this.name = name;
        this.type = type;
        this.stack = stack;
    }

    public ChartSeries() {
    }
}
