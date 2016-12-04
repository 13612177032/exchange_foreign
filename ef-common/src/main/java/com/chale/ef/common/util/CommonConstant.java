package com.chale.ef.common.util;

/**
 * Created by liangcl on 2016/12/3.
 */
public class CommonConstant {
    public enum  CURRENCY {
        USD,
        HKD;
    }
    public static boolean checkSupportCurrency(String currency){
        for (CURRENCY c:CURRENCY.values()) {
            if(c.name().equals(currency)) return true;
        }
        return false;
    }
    public static boolean checkSupportExchangeType(String exchangeType){

        return EXCHANGE_TYPE_BUY.equals(exchangeType) || EXCHANGE_TYPE_SALE.equals(exchangeType);
    }

    public final static String EXCHANGE_TYPE_BUY="1";
    public final static String EXCHANGE_TYPE_SALE="2";

    public final static String ORDER_STATUS_SUBMIT="01";
    public final static String ORDER_STATUS_BACKD="02";

    public final static String COLLECT_STATUS_BEGIN="01";
    public final static String COLLECT_STATUS_FAIL="02";
    public final static String COLLECT_STATUS_SUCCESS="03";

}
