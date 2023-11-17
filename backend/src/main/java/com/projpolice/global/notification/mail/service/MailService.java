package com.projpolice.global.notification.mail.service;

import java.util.Collection;

import org.springframework.scheduling.annotation.Async;

import com.projpolice.global.notification.mail.dto.MailDto;

public interface MailService {

    @Async
    void sendMail(MailDto mail);

    @Async
    void sendMail(Collection<MailDto> mail);
}
