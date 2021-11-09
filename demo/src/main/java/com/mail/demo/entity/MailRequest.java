package com.mail.demo.entity;


import javax.validation.constraints.NotEmpty;

public class MailRequest {
    @NotEmpty
    private String subject;
    @NotEmpty
    private String content;
    @NotEmpty
    private String[] receivers;

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String[] getReceivers() {
        return receivers;
    }
}
