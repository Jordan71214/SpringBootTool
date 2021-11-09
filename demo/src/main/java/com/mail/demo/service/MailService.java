package com.mail.demo.service;

import com.mail.demo.entity.MailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class MailService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private JavaMailSenderImpl javaMailSender;

    private static String HOST = "smtp.gmail.com";
    private static int PORT = 587;
    private static boolean AUTH_ENABLE = true;
    private static boolean STARTTLS_ENABLE = true;
    private static String PROTOCOL = "smtp";
    private static String USERNAME = "your_email_address@gmail.com";
    private static String PASSWORD = "your_password";

    @PostConstruct
    public void init() {
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(HOST);
        javaMailSender.setPort(PORT);
        javaMailSender.setUsername(USERNAME);
        javaMailSender.setPassword(PASSWORD);

        //        Properties properties = javaMailSender.getJavaMailProperties();

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", AUTH_ENABLE);
        properties.put("mail.smtp.starttls.enable", STARTTLS_ENABLE);
        properties.put("mail.transport.protocol", PROTOCOL);

        javaMailSender.setJavaMailProperties(properties);

    }

    public void sendMail(MailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USERNAME);
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
