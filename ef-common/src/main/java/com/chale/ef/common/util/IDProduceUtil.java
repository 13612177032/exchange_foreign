package com.chale.ef.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liangcl on 2016/12/3.
 */
public class IDProduceUtil {
    private static Object lockObj = "lockerOrder";
    private static long orderNumCount = 0L;
    private static int maxPerMSECSize=10000;

    /**
     * 单机且每秒小于1W时可以使用，多机时可用redis递增的方式生成
     * @return
     */
    public static String makeOrderNum() {
         synchronized (lockObj) {
            // 取系统当前时间作为订单号变量前半部分，精确到毫秒
            long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
            // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
            if (orderNumCount > maxPerMSECSize) {
                orderNumCount = 0L;
            }
            //组装订单号
            String countStr=maxPerMSECSize +orderNumCount+"";
            orderNumCount++;
            return nowLong+countStr.substring(1);
         }
    }

    public static String produceEfId() {
        return "EF"+makeOrderNum();
    }
}
