package com.projpolice.global.notification.mail.service;

import com.projpolice.global.notification.mail.dto.MailDto;

public interface MailService {
    void sendMail(MailDto mail);
}
