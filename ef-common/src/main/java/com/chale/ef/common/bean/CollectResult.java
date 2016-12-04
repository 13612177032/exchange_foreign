package com.chale.ef.common.bean;

import java.math.BigDecimal;

/**
 * Created by liangcl on 2016/12/3.
 */
public class CollectResult {
    private String currency;
    private BigDecimal allForeignAmount;
    private BigDecimal allMoney;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
