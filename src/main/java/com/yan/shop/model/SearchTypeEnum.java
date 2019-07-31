package com.yan.shop.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yan on  24/01/2019.
 */
public enum SearchTypeEnum {

    YEAR(1, "%Y"),//按年
    MONTH(2, "%Y-%m"),//按月
    DAY(3, "%Y-%m-%d"),//按日
    HOUR(4, "%Y-%m-%d-%H时");//按时

    private Integer code;

    private String desc;

    SearchTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static class Holder {

        private static final Map<Integer, SearchTypeEnum> MAP = new HashMap<>();

        static {
            for (SearchTypeEnum e : values()) {
                MAP.put(e.code, e);
            }
        }

        public static SearchTypeEnum parse(int value) {
            return MAP.get(value);
        }

    }

}
