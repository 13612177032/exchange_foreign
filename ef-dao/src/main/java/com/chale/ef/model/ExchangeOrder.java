package com.chale.ef.model;

import com.chale.ef.common.util.CommonConstant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liangcl on 2016/12/3.
 */
public class ExchangeOrder {
    private long id;
    private String orderId;
    private String userPin;
    private BigDecimal foreignAmount;
    private BigDecimal amount;
    private BigDecimal exchangeRate;
    private String currency;
    private String exchangeType;
    private String status;
    private Date createTime;


    public ExchangeOrder() {
        super();
    }

    public ExchangeOrder(long id, String orderId, String userPin, BigDecimal amount,BigDecimal foreignAmount,  BigDecimal exchangeRate,  String currency, String exchangeType, String status, Date createTime) {
        this.id = id;
        this.orderId = orderId;
        this.userPin = userPin;
        this.foreignAmount = foreignAmount;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.currency = currency;
        this.exchangeType = exchangeType;
        this.status = status;
        this.createTime = createTime;
    }

    public ExchangeOrder(String orderId, String userPin, BigDecimal amount, BigDecimal foreignAmount, BigDecimal exchangeRate, String currency, String exchangeType, String status) {
        this.orderId = orderId;
        this.userPin = userPin;
        this.foreignAmount = foreignAmount;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.currency = currency;
        this.exchangeType = exchangeType;
        this.status = status;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
