package com.gotop.order_service.mailService;

import com.gotop.order_service.domain.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    public void send(String subject,String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailProperties.getFrom());
        simpleMailMessage.setTo(mailProperties.getTo());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }

}
