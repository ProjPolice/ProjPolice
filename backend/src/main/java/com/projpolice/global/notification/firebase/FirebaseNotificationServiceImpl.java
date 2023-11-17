package com.projpolice.global.notification.firebase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushFcmOptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseNotificationServiceImpl implements FirebaseNotificationService {

    @Value("${projpolice.icon.path}")
    private String iconPath;

    @Value("${projpolice.base.url}")
    private String baseUrl;

    private final FirebaseMessaging firebaseMessaging;

    private void send(String title, String body, String fcmToken, String url) {
        Notification notification = Notification.builder()
            .setTitle(title)
            .setBody(body)
            .setImage(iconPath)
            .build();

        WebpushConfig webpushConfig = WebpushConfig.builder()
            .setFcmOptions(
                WebpushFcmOptions.builder()
                    .setLink(url)
                    .build()
            ).build();

        Message message = Message.builder()
            .setToken(fcmToken)
            .setNotification(notification)
            .setWebpushConfig(webpushConfig)
            .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            log.error("[FCM Message Error] : {}", e.getMessage());
        }
    }

    private void send(String title, String body, Collection<String> fcmToken, String url) {
        Notification notification = Notification.builder()
            .setTitle(title)
            .setBody(body)
            .setImage(iconPath)
            .build();

        WebpushConfig webpushConfig = WebpushConfig.builder()
            .setFcmOptions(
                WebpushFcmOptions.builder()
                    .setLink(url)
                    .build()
            ).build();
        List<Message> messages = new ArrayList<>();
        for (String token : fcmToken) {
            messages.add(
                Message.builder()
                    .setToken(token)
                    .setNotification(notification)
                    .setWebpushConfig(webpushConfig)
                    .build()
            );
        }

        try {
            firebaseMessaging.sendAll(messages);
        } catch (FirebaseMessagingException e) {
            log.error("[FCM Message Error] : {}", e.getMessage());
        }
    }

    @Override
    public void userInvitedToProject(long projectId, String title, String body, String fcmToken) {
        final String url = String.format("%s/", baseUrl + "project/" + projectId);
        send(title, body, fcmToken, url);
    }

    @Override
    public void userRemovedFromProject(long projectId, String title, String body, String fcmToken) {
        final String url = String.format("%s/", baseUrl);
        send(title, body, fcmToken, url);
    }

    @Override
    public void taskAssignedToUser(long taskId, String title, String body, String fcmToken) {
        final String url = String.format("%s/", baseUrl);
        send(title, body, fcmToken, url);
    }

    @Override
    public void taskChanged(long taskId, String title, String body, Collection<String> fcmTokens) {
        final String url = String.format("%s/", baseUrl);
        send(title, body, fcmTokens, url);
    }
}
