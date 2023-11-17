package com.projpolice.global.common.meta.converter;

import com.projpolice.global.common.error.exception.MetaException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.meta.domain.TaskStatus;

import jakarta.persistence.AttributeConverter;

public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {
    @Override
    public String convertToDatabaseColumn(TaskStatus attribute) {
        return attribute.name();
    }

    @Override
    public TaskStatus convertToEntityAttribute(String dbData) {
        TaskStatus taskStatus = null;
        try {
            taskStatus = TaskStatus.valueOf(dbData.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MetaException(ExceptionInfo.INVALID_METADATA);
        }
        return taskStatus;
    }
}
