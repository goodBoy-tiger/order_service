package com.gotop.order_service.controller;

import com.gotop.order_service.mailService.MailService;
import com.gotop.order_service.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/v1/order")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MailService mailService;

    @RequestMapping("/save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId, HttpServletRequest request){
        String cookie = request.getHeader("cookie");
        String token = request.getHeader("token");
        System.out.println("cookie="+cookie);
        System.out.println("token="+token);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("code",0);
        dataMap.put("data",productOrderService.save(userId,productId));
        return dataMap;
    }

    private Object saveOrderFail(@RequestParam("user_id") int userId,@RequestParam("product_id") int productId, HttpServletRequest request){
        String saveOrderKey = "save-order";

        String key = redisTemplate.opsForValue().get(saveOrderKey);
        new Thread(()->{
            if(StringUtils.isBlank(key)){
                System.out.println("紧急邮件，用户下单失败，请立刻排查");
                redisTemplate.opsForValue().set(saveOrderKey,"save-order-fail",1, TimeUnit.MINUTES);
                //发送邮件到相关负责人
                mailService.send("紧急邮件","用户下单失败，请立刻排查！");
            }else{
                System.out.println("已经发送邮件，1分钟内不在重复发送");
            }
        }).start();

        Map<String,Object> map = new HashMap<>();
        map.put("code",-1);
        map.put("msg","当前请求人数过多，您被挤出来了，请稍后再试");
        return map;
    }
}
