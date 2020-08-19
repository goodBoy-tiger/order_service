package com.gotop.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.gotop.order_service.domain.ProductOrder;
import com.gotop.order_service.service.ProductClient;
import com.gotop.order_service.service.ProductOrderService;
import com.gotop.order_service.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    /**
     * ribbon方法调用服务
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * feign方法调用服务
     */
    @Autowired
    private ProductClient productClient;

    @Override
    public ProductOrder save(int userId, int productId) {
        String response = productClient.findById(productId);
        JsonNode jsonNode = JsonUtils.str2jsonNode(response);
        System.out.println(jsonNode);
        //Map<String,Object> productMap = restTemplate.getForObject("http://product-service/api/v1/product/find?id="+productId,Map.class);
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setUserId(userId);
        //productOrder.setProductName(productMap.get("name").toString());
        //productOrder.setPrice(Integer.parseInt(productMap.get("price").toString()));
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
