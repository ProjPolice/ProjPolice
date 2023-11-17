package com.projpolice.global.notification.firebase;

import java.util.Collection;

public interface FirebaseNotificationService {

    void userInvitedToProject(long projectId, String title, String body, String fcmToken);

    void userRemovedFromProject(long projectId, String title, String body, String fcmToken);

    void taskAssignedToUser(long taskId, String title, String body, String fcmToken);

    void taskChanged(long taskId, String title, String body, Collection<String> fcmTokens);
}
