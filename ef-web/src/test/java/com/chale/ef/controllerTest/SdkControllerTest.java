package com.chale.ef.controllerTest;


import org.junit.Before;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by liangchaolei on 2016/8/29.
 */

public class SdkControllerTest {


    private static final String BASE_URL = "http://localhost:8080/foreign/";
    RestTemplate rest;
    @Before
    public void begin(){
        rest=new RestTemplate();
    }

    @Test
    public void sale(){


        MultiValueMap<String,Object> map=new LinkedMultiValueMap<String, Object>();
        map.add("userPin","testPin");
        map.add("foreignAmount","222.22");
        map.add("foreignCurrency","USD");
        map.add("amount","111.11");
        map.add("exchangeRate","2");
        map.add("exchangeType","1");
        String res = rest.postForObject(BASE_URL+"sale.do", map , String.class);
        System.out.println(res);
    }


    @Test
    public void back(){
        MultiValueMap<String,String> map=new LinkedMultiValueMap<String, String>();
        map.add("orderId","EF201612041410551990002");
        String res = rest.postForObject(BASE_URL+"back.do", map , String.class);
        System.out.println(res);
    }






}
