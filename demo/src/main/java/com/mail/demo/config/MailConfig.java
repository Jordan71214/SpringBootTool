package com.mail.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:mail.properties")
public class MailConfig {

    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private int port;
    @Value("${mail.auth.enable}")
    private boolean authEnable;
    @Value("${mail.starttls.enable}")
    private boolean starttlsEnable;
    @Value("${mail.protocol}")
    private String protocol;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isAuthEnable() {
        return authEnable;
    }

    public boolean isStarttlsEnable() {
        return starttlsEnable;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
