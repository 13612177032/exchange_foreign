package com.chale.ef.web.controller;



import com.chale.ef.common.bean.exchange.BackRequest;
import com.chale.ef.common.bean.exchange.BackResonse;
import com.chale.ef.common.exception.BusinessException;
import com.chale.ef.common.exception.ExceptionEmnu;
import com.chale.ef.service.ExchangeService;
import com.chale.ef.common.bean.exchange.SaleRequest;
import com.chale.ef.common.bean.exchange.SaleResonse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liangchaolei on 2016/8/29.
 */
@Controller
@RequestMapping(value = "/foreign")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    /**
     * 销售接口
     * @param request 订单信息
     * @return 订单号
     * @throws BusinessException
     */
    @RequestMapping(value = "/sale.do",method = RequestMethod.POST)
    @ResponseBody
    public SaleResonse sale(SaleRequest request) throws BusinessException{
        checkNull(request,"请求参数不能为空");
        checkNull(request.getUserPin(),"用户名不能为空");
        checkNull(request.getAmount(),"金额不能为空");
        checkNull(request.getForeignAmount(),"外币金额不能为空");
        checkNull(request.getExchangeType(),"兑换类型不能为空");
        checkNull(request.getForeignCurrency(),"外币类型不能为空");
        checkNull(request.getExchangeRate(),"汇率不能为空");
        if(request.getAmount().doubleValue()<=0 ||
           request.getForeignAmount().doubleValue()<=0||
           request.getExchangeRate().doubleValue()<=0)
            throw new BusinessException(ExceptionEmnu.MINUS_ERROR);
        return exchangeService.sale(request);
    }

    /**
     * 撤销接口
     * @param request 订单号信息
     * @return 撤销结果
     * @throws BusinessException
     */
    @RequestMapping(value = "/back.do")
    @ResponseBody
    public BackResonse sale(BackRequest request) throws BusinessException{
        checkNull(request,"请求参数不能为空");
        checkNull(request.getOrderId(),"订单号不能为空");
        return exchangeService.back(request);
    }

    /**
     * 空置判断
     * @param v
     * @param mes
     * @throws BusinessException
     */
    private void checkNull(Object v,String mes) throws BusinessException{
            if(v==null || "".equals(v)) throw new BusinessException(ExceptionEmnu.NULL,mes);
    }


}
