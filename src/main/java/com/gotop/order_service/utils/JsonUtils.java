package com.gotop.order_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * json工具类
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json字符串转jsonNode对象方法
     * @param str
     * @return
     */
    public static JsonNode str2jsonNode(String str){
        try {
            return objectMapper.readTree(str);
        } catch (IOException e) {
            return null;
        }
    }
}
