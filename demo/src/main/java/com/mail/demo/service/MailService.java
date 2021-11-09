package com.mail.demo.service;

import com.mail.demo.config.MailConfig;
import com.mail.demo.entity.MailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class MailService {


    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MailConfig mailConfig;
    private JavaMailSenderImpl javaMailSender;


    @PostConstruct
    public void init() {
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailConfig.getHost());
        javaMailSender.setPort(mailConfig.getPort());
        javaMailSender.setUsername(mailConfig.getUsername());
        javaMailSender.setPassword(mailConfig.getPassword());

        //        Properties properties = javaMailSender.getJavaMailProperties();

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", mailConfig.isAuthEnable());
        properties.put("mail.smtp.starttls.enable", mailConfig.isStarttlsEnable());
        properties.put("mail.transport.protocol", mailConfig.getProtocol());

        javaMailSender.setJavaMailProperties(properties);

    }

    public void sendMail(MailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailConfig.getUsername());
        message.setTo(request.getReceivers());
        message.setSubject(request.getSubject());
        message.setText(request.getContent());

        try {
            javaMailSender.send(message);
        } catch (MailAuthenticationException e) {
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
