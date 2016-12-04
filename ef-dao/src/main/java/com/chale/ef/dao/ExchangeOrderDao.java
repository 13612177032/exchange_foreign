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
    /**
     * 保存订单
     * @param order
     */
    void saveOrder(ExchangeOrder order);

    /**
     * 根据订单号查询订单
     * @param orderId
     * @return
     */
    ExchangeOrder queryByOrderId(String orderId);

    /**
     * 汇总信息
     * @param map
     * @return
     */
    List<CollectResult> collect(Map<String,Object> map);

    /**
     * 更新订单
     * @param orderId
     */
    void update(ExchangeOrder orderId);

}
