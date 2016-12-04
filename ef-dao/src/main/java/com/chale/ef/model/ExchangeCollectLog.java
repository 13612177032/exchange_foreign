package com.chale.ef.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liangcl on 2016/12/3.
 */
public class ExchangeCollectLog {
    private long id;
    private String status;
    private String remark;
    private Date createTime;


    public ExchangeCollectLog() {
        super();
    }

    public ExchangeCollectLog(long id, String status, String remark, Date createTime) {
        this.id = id;
        this.status = status;
        this.remark = remark;
        this.createTime = createTime;
    }

    public ExchangeCollectLog(String status) {

        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
