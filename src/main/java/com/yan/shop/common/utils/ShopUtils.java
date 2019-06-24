package com.yan.shop.common.utils;

import org.slf4j.Logger;

import java.util.Map;

/**
 * Created by yan on  21/06/2019.
 */
public class ShopUtils {

   public static boolean buildRes(Map<String, Object> data, int i, Logger logger, Long id) {
        int result = i;
        if(result == 0){
            data.put("code",0);
            data.put("msg","删除失败");
            logger.error("删除失败");
            return true;
        }
        data.put("code",1);
        data.put("msg","删除成功");
        logger.info("删除成功");
        return false;
    }

}
