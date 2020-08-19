package com.gotop.order_service.domain;

import java.io.Serializable;
import java.util.Date;

public class ProductOrder implements Serializable {

    private int id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格，分
     */
    private int price;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户ID
     */
    private int userId;

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
