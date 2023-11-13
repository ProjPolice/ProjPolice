package com.projpolice.global.notification.mail.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailDto {

    private String emailAddress;

    private String title;

    private String body;
}
