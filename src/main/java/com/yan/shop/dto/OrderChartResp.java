package com.yan.shop.dto;

import java.util.Arrays;

/**
 * Created by yan on  30/07/2019.
 */
public class OrderChartResp {

    private String[] legend;

    private String[] xAxis;

    private ChartSeries[] series;

    public String[] getLegend() {
        return legend;
    }

    public void setLegend(String[] legend) {
        this.legend = legend;
    }

    public String[] getxAxis() {
        return xAxis;
    }

    public void setxAxis(String[] xAxis) {
        this.xAxis = xAxis;
    }

    public ChartSeries[] getSeries() {
        return series;
    }

    public void setSeries(ChartSeries[] series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "DriverChartResp{" +
                "legend=" + Arrays.toString(legend) +
                ", xAxis=" + Arrays.toString(xAxis) +
                ", series=" + Arrays.toString(series) +
                '}';
    }
}
