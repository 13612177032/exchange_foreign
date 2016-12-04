package com.chale.ef.service;

import com.chale.ef.common.bean.exchange.BackRequest;
import com.chale.ef.common.bean.exchange.BackResonse;
import com.chale.ef.common.bean.exchange.SaleRequest;
import com.chale.ef.common.bean.exchange.SaleResonse;
import com.chale.ef.common.exception.BusinessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liangcl on 2016/12/3.
 */
public interface ExchangeService {
    @Transactional
    SaleResonse sale(SaleRequest request) throws BusinessException;
    @Transactional
    BackResonse back(BackRequest request) throws BusinessException;

    void collect();
}
