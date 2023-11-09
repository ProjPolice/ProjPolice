package com.projpolice.global.notification.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NotificationWebPush {
    @Builder.Default
    private Map<String, String> headers = new HashMap<>();

    @Builder.Default
    private Map<String, String> data = new HashMap<>();

    private NotificationModalData notification;

    private NotificationOption fcm_options;
}
