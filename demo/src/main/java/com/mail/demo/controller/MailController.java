package com.mail.demo.controller;

import com.mail.demo.entity.MailRequest;
import com.mail.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/mail", produces = MediaType.APPLICATION_JSON_VALUE)
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@Valid @RequestBody MailRequest request) {
        mailService.sendMail(request);
        return ResponseEntity.noContent().build();
    }

}
