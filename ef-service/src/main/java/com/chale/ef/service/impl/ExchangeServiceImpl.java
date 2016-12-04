package com.chale.ef.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.chale.ef.common.bean.BaseResponse;
import com.chale.ef.common.bean.CollectResult;
import com.chale.ef.common.bean.exchange.BackRequest;
import com.chale.ef.common.bean.exchange.BackResonse;
import com.chale.ef.common.bean.exchange.SaleRequest;
import com.chale.ef.common.bean.exchange.SaleResonse;
import com.chale.ef.common.exception.BusinessException;
import com.chale.ef.common.exception.ExceptionEmnu;
import com.chale.ef.common.util.CommonConstant;
import com.chale.ef.common.util.CommonConstant.*;
import com.chale.ef.common.util.IDProduceUtil;
import com.chale.ef.dao.ExchangeCollectLogDao;
import com.chale.ef.dao.ExchangeOrderDao;
import com.chale.ef.model.ExchangeCollectLog;
import com.chale.ef.model.ExchangeOrder;
import com.chale.ef.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chale.ef.common.util.CommonConstant.*;


/**
 * 外汇交易service
 * Created by liangcl on 2016/12/3.
 */
@Service
public class ExchangeServiceImpl implements ExchangeService{
    @Autowired
    private ExchangeOrderDao exchangeOrderDao;
    @Autowired
    private ExchangeCollectLogDao exchangeCollectLogDao;
    @Override
    public SaleResonse sale(SaleRequest request) throws BusinessException{
        //币种验证
        if(!CommonConstant.checkSupportCurrency(request.getForeignCurrency()))  throw new BusinessException(ExceptionEmnu.NOT_SUPPORT_CURRENCY);
        //交易方式验证
        if(!CommonConstant.checkSupportExchangeType(request.getExchangeType()))  throw new BusinessException(ExceptionEmnu.NOT_SUPPORT_EXCHANGE_TYPE);
        //金额准确性验证
        if(!checkMoney(request)) throw new BusinessException(ExceptionEmnu.MONEY_NOT_EQ);
        //买入入库为负
        BigDecimal fh=new BigDecimal(EXCHANGE_TYPE_BUY.equals(request.getExchangeType())?-1:1);
        ExchangeOrder order=new ExchangeOrder(
                //多机部署需要更换订单号生成策略
                IDProduceUtil.produceEfId(),
                request.getUserPin(),
                //如果时买进则为负数
                request.getAmount().multiply(fh),
                //如果时买进则为负数
                request.getForeignAmount().multiply(fh),
                request.getExchangeRate(),
                request.getForeignCurrency(),
                request.getExchangeType(),
                ORDER_STATUS_SUBMIT
        );
        this.exchangeOrderDao.saveOrder(order);
        //返回结果
        SaleResonse saleResponse=new SaleResonse();
        saleResponse.setOrderId(order.getOrderId());
        return saleResponse;
    }



    @Override
    public BackResonse back(BackRequest request)  throws BusinessException{
        //查询该订单
        ExchangeOrder order=this.exchangeOrderDao.queryByOrderId(request.getOrderId());
        if(order == null ) throw new BusinessException(ExceptionEmnu.RECALL_FAIL,"订单为空");
        //判断订单状态
        if(!ORDER_STATUS_SUBMIT.equals(order.getStatus()))  throw new BusinessException(ExceptionEmnu.RECALL_FAIL,"该订单状态已改变，无法撤销");
        //判断最后一次汇总时间
        ExchangeCollectLog log=this.exchangeCollectLogDao.getLatestLog(new HashMap<String,Object>());
        //易产生汇总
        if(log!=null && log.getCreateTime().after(order.getCreateTime())) throw new BusinessException(ExceptionEmnu.RECALL_FAIL,"该订单易产生汇总");
        //撤销订单
        this.backOrder(request.getOrderId());

        return new BackResonse();
    }



    @Override
    public void collect() {
        Date collectTime=new Date();

        //记录状态 --开始汇总
        ExchangeCollectLog log=new ExchangeCollectLog(COLLECT_STATUS_BEGIN);
        log.setCreateTime(collectTime);
        this.exchangeCollectLogDao.save(log);

        //查询上一次成功的时间
        ExchangeCollectLog latestCollect=this.exchangeCollectLogDao.getLatestLog(new HashMap<String, Object>(){{put("status",COLLECT_STATUS_SUCCESS);}});
        //为空从头开始查询
        Date beginDate=latestCollect!=null && latestCollect.getCreateTime()!=null?latestCollect.getCreateTime():new Date(0);

        //汇总结果
        List<CollectResult> results= this.collectAmount(beginDate,collectTime);

        //TODO
        //汇总结果暂以json存入
        log.setRemark(results==null || results.size()==0?"NULL":JSONObject.toJSONString(results));
        log.setStatus(COLLECT_STATUS_SUCCESS);

        //更新状态
        this.exchangeCollectLogDao.updateRemark(log);

    }

    private List<CollectResult> collectAmount(Date createTime, Date collectTime) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("beginTime",createTime);
        map.put("endTime",collectTime);
        map.put("status",ORDER_STATUS_SUBMIT);
        return this.exchangeOrderDao.collect(map);
    }

    private boolean checkMoney(SaleRequest request) {
        return request.getAmount().multiply(request.getExchangeRate()).compareTo(request.getForeignAmount())==0;
    }
    private void backOrder(String orderId) {
        ExchangeOrder order=new ExchangeOrder();
        order.setOrderId(orderId);
        order.setStatus(ORDER_STATUS_BACKD);
        this.exchangeOrderDao.update(order);
    }
}
