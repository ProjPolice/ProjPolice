package com.projpolice.domain.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.global.notification.mail.dto.MailDto;
import com.projpolice.global.notification.mail.service.MailService;

import lombok.RequiredArgsConstructor;

// TODO: Remove after test
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final MailService mailService;

    @PostMapping("/mail")
    public String emailTest(@RequestBody MailDto mail) {

        return "Send";
    }
}
