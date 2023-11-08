package com.projpolice.global.common.deletion;

public interface DeletionService {
    void deleteTask(long taskId);

    void deleteEpic(long epicId);

    void deleteProject(long projectId);
}
