package com.projpolice.domain.epic.response;

import java.util.List;

import com.projpolice.domain.epic.dto.EpicProjectionData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpicProjectionResponse {
    List<EpicProjectionData> epics;
}
