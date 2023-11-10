package com.projpolice.global.firebase;

import com.projpolice.domain.task.dto.TaskChangePackagingDto;

public interface NotificationService {

    // 프로젝트에 사용자가 추가 되었을 때
    void userInvitedToProject(long projectId, long userId);

    // 프로젝트가 사용자가 제거 되었을 때
    void userRemovedFromProject(long projectId, long userId);

    // task가 사용자에게 할당 되었을 때
    void taskAssignedToUser(long taskId, long userId);

    // task가 변경 되었을 때
    void taskChanged(long taskId, long invokedUserId, TaskChangePackagingDto changes);
}
