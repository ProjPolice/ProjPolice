package com.projpolice.global.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NotificationOption {
    private String link;
    private String analytics_label;
}
