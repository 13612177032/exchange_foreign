package com.chale.ef.dao;

import com.chale.ef.common.bean.CollectResult;
import com.chale.ef.model.ExchangeOrder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liangchaolei on 2016/9/8.
 */
@Repository
public interface ExchangeOrderDao {

    void saveOrder(ExchangeOrder order);


    ExchangeOrder queryByOrderId(String orderId);

    List<CollectResult> collect(Map<String,Object> map);

    void update(ExchangeOrder orderId);

}
