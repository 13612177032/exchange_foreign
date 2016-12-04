package com.chale.ef.common.bean.exchange;


import com.chale.ef.common.util.CommonConstant;

import java.math.BigDecimal;

/**
 * Created by liangcl on 2016/12/3.
 */
public class SaleRequest {

    private String userPin;
    private BigDecimal foreignAmount;
    private String foreignCurrency;
    private BigDecimal amount;
    private BigDecimal exchangeRate;
    private String exchangeType;


    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    public BigDecimal getForeignAmount() {
        return foreignAmount;
    }

    public void setForeignAmount(BigDecimal foreignAmount) {
        this.foreignAmount = foreignAmount;
    }

    public String getForeignCurrency() {
        return foreignCurrency;
    }

    public void setForeignCurrency(String foreignCurrency) {
        this.foreignCurrency = foreignCurrency;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }


}
