package com.projpolice.domain.task.dto;

import java.time.LocalDate;

import com.projpolice.global.common.meta.domain.TaskStatus;

import lombok.Getter;

@Getter
public class TaskChangePackagingDto {
    private final String[] name;
    private final String[] description;
    private final LocalDate[] startDate;
    private final LocalDate[] endDate;
    private final TaskStatus[] status;
    private final long[] userId;
    private final long[] epicId;

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String[] name;
        private String[] description;
        private LocalDate[] startDate;
        private LocalDate[] endDate;
        private TaskStatus[] status;
        private long[] userId;
        private long[] epicId;

        private Builder() {

        }

        public Builder name(String before, String after) {
            this.name = new String[] {before, after};
            return this;
        }

        public Builder description(String before, String after) {
            this.description = new String[] {before, after};
            return this;
        }

        public Builder startDate(LocalDate before, LocalDate after) {
            this.startDate = new LocalDate[] {before, after};
            return this;
        }

        public Builder endDate(LocalDate before, LocalDate after) {
            this.endDate = new LocalDate[] {before, after};
            return this;
        }

        public Builder status(TaskStatus before, TaskStatus after) {
            this.status = new TaskStatus[] {before, after};
            return this;
        }

        public Builder userId(Long before, Long after) {
            this.userId = new long[] {before, after};
            return this;
        }

        public Builder epicId(Long before, Long after) {
            this.epicId = new long[] {before, after};
            return this;
        }

        public TaskChangePackagingDto build() {
            return new TaskChangePackagingDto(this);
        }
    }

    private TaskChangePackagingDto(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.status = builder.status;
        this.userId = builder.userId;
        this.epicId = builder.epicId;
    }
}
