package com.chale.ef.dao;

import com.chale.ef.model.ExchangeCollectLog;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * 汇总记录表，不必要存入数据库，
 * 本次汇总信息会以一个json的形式计入remark字段，不太符合第三范式
 * Created by liangcl on 2016/12/3.
 */
@Repository
public interface ExchangeCollectLogDao {
    /**
     * 获取最近一次更新
     * @return
     * @param status
     */
    ExchangeCollectLog getLatestLog(HashMap<String, Object> status);

    /**
     * 保存汇总日志
     * @param log
     * @return
     */
    long save(ExchangeCollectLog log);

    /**
     * 更新汇总日志
     * @param log
     */
    void updateRemark(ExchangeCollectLog log);
}
