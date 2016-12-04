package com.chale.ef.common.bean;

import java.math.BigDecimal;

/**
 * Created by liangcl on 2016/12/3.
 */
public class CollectResult {
    private String exchangeType;
    private BigDecimal allForeignAmount;
    private BigDecimal allMoney;

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public BigDecimal getAllForeignAmount() {
        return allForeignAmount;
    }

    public void setAllForeignAmount(BigDecimal allForeignAmount) {
        this.allForeignAmount = allForeignAmount;
    }

    public BigDecimal getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(BigDecimal allMoney) {
        this.allMoney = allMoney;
    }
}
