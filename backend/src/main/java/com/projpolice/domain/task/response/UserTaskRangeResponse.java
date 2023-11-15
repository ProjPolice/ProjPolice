package com.projpolice.domain.task.response;

import java.util.List;

import com.projpolice.domain.task.dto.TaskRelatedProjectionData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskRangeResponse {
    List<TaskRelatedProjectionData> tasks;
}
