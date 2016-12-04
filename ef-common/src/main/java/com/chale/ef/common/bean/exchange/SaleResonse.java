package com.chale.ef.common.bean.exchange;

import com.chale.ef.common.bean.BaseResponse;
import com.chale.ef.common.exception.ExceptionEmnu;

/**
 * Created by liangcl on 2016/12/3.
 */
public class SaleResonse extends BaseResponse{
    private String orderId;

    public SaleResonse() {
        super(ExceptionEmnu.SUCCESS);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
