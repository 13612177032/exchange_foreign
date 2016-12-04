## 外汇交易系统

### 1. 需求
设计一个简单的交易系统，提供人民币买卖外币的功能，接收用户发送过来的买卖请求，将外币卖给用户或者买入用户的外币，并定时汇总这些交易数据，生成汇总信息，后面会根据这些汇总信息去找银行买入或者卖出这些外币，支持的外币币种只有有两个币种：美元USD、港币HKD，

#### 需要实现的功能列表如下：

1. 能够接受用户发送过来的交易请求，交易请求中的关键要素有：用户、外币金额、外币币种、人民币金额、汇率、买卖方向等，外币交易方向可能有买入或者卖出
2. 添加一个定时任务，每隔一个小时从请求表中汇总各币种的净交易金额，生成汇总信息，据此跟银行去买入或者卖出相应的外币（只需生成汇总信息即可）
3. 需要给用户提供交易撤销功能，只要用户的这笔交易请求还没有被汇总就可以被撤销，但是一笔交易只能被撤销一次，撤销之后不可以再次撤销。

#### 其他非功能要求如下：

1. 这个系统中需要使用关系数据库（oracle、mysql或者sqlserver），提供数据库建表语句即可，建表语句中要包括数据库表的索引、主键等信息。
2. 此系统不需要界面，只需要提供交易请求接口及接口实现的代码即可，可以通过测试用例进行测试。
3. 使用java语言编写，可以使用spring、ibatis/hibernate、quartz等常用框架。
4. 不需要考虑实际资金流转，仅需要实现上述系统功能即可。

### 2.数据库设计

```
-- 订单表
DROP TABLE IF EXISTS `exchange_order`;
CREATE TABLE `exchange_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` varchar(32) NOT NULL COMMENT '订单号',
  `user_pin` varchar(32) NOT NULL COMMENT '用户pin',
  `foreign_amount` decimal(16,4) NOT NULL COMMENT '外币金额，如果用户买进为负，用户售出为正',
  `amount` decimal(16,4) NOT NULL COMMENT '人民币金额，如果用户买进为负，用户售出为正',
  `currency` char(3) NOT NULL COMMENT '币种',
  `exchange_rate` decimal(16,4) DEFAULT NULL COMMENT '汇率',
  `exchange_type` char(1) NOT NULL COMMENT '交易类型：1购买，2售出',
  `status` char(2) NOT NULL COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`order_id`),
  INDEX (`user_pin`),
  INDEX (`create_time`)
)
-- 汇总信息日志表
-- 本表为定时任务后生成的结果表，
-- 本表设计时未考虑第三范式，remark为结果的json串，正式应用需要拆分成两个表
DROP TABLE IF EXISTS `exchange_collect_log`;
CREATE TABLE `exchange_collect_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `remark` varchar(1000) DEFAULT NULL COMMENT '汇总信息',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '汇总时间',
  PRIMARY KEY (`id`),
  INDEX (`create_time`)
)
```

### 3.接口设计
1. 交易接口
    接口地址： http://localhost:8080/foreign/sale.do  
    参数：      userPin ：用户pin  
               foreignAmount ：外币数量  
               foreignCurrency ：外币币种，目前仅支持USD,HKD  
               amount ：人民币金额  
               exchangeRate ：汇率  
               exchangeType ：交易类型 1：买进 2：售出  
    返回信息：
                   code: 1000未成功，其余为失败  
                   msg:失败信息  
                   orderId:订单号  
2. 撤销接口
    接口地址： http://localhost:8080/foreign/back.do  
            参数：      orderId ：订单号  
            返回信息：  
                   code: 1000未成功，其余为失败  
                   msg:失败信息  
*.附：错误码与错误信息对应  
   1001, "验证失败"  
   1002, "撤销失败"  
   1003, "空值异常"  
   1004, "金额计算不正确，请验证"  
   1005, "不支持该币种"  
   1006, "不支持该交易类型"  
   9999, "系统异常"  

### 4.其它

1. 本系统采用了springMVC + Spring + Mybatis
2. 目前未考虑分库分表，如果有需求，可以按照订单时间和订单号HASH进行，分库分表后需要改进汇总策略，汇总汇总日期内所有表的信息后，再进行汇总的方式，
3. 目前未考虑多实例运行情况，多实例运行下，需要改进订单号生成策略（可利用redis计数规则）；需要改进定时器规则，将定时器改成抢任务运行（备灾）
4. 真实线上环境中可将接口服务与定时服务拆分成两个系统
