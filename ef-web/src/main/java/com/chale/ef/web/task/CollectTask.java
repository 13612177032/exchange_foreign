package com.chale.ef.web.task;

import com.chale.ef.service.ExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时汇总任务
 * Created by liangcl on 2016/12/3.
 */
public class CollectTask {
    private static final Logger logger = LoggerFactory.getLogger(CollectTask.class);

    @Autowired
    private ExchangeService exchangeService;

    public void collect() {
        try {
            logger.info("CollectTask.collect.begin");
            exchangeService.collect();
        }catch (Exception e){
            logger.info("CollectTask.collect.error",e);

        }
    }
}
