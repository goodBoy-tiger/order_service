package com.gotop.order_service.fallback;

import com.gotop.order_service.service.ProductClient;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findById(int id) {
        System.out.println("feign 调用商品服务 product-client findById 异常");
        return null;
    }
}
