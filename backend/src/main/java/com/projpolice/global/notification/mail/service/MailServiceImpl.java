package com.projpolice.global.notification.mail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.projpolice.global.notification.mail.dto.MailDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    @Value("${projpolice.mail.sender}")
    private String sender;

    @Value("${projpolice.mail.domain}")
    private String domain;

    @Override
    public void sendMail(MailDto mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(mail.getEmailAddress());
        message.setSubject(mail.getTitle());
        message.setText(mail.getBody());
        mailSender.send(message);
    }
}
