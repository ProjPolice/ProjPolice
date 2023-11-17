package com.projpolice.domain.task.domain.redis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.projpolice.domain.task.dto.TaskRelatedProjectionData;

import lombok.Builder;
import lombok.Getter;

@Getter
@RedisHash(value = "user-task-projection", timeToLive = 2 * 60 * 60) // 2시간
public class UserTaskRedisData {

    @Id
    private String id;

    @Builder.Default
    List<TaskRelatedProjectionData> task = new ArrayList<>();

    private UserTaskRedisData() {
    }

    public static String id(long userId, LocalDate startDate, LocalDate endDate) {
        return String.format("%d-%s-%s", userId, startDate.toString(), endDate.toString());
    }

    public UserTaskRedisData(long userId, LocalDate startDate, LocalDate endDate,
        List<TaskRelatedProjectionData> task) {
        this.id = id(userId, startDate, endDate);
        this.task = task;
    }
}
