package com.gotop.order_service.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件实体类
 */
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String from;
    private String to;
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
