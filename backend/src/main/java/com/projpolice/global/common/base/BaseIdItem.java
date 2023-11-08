package com.projpolice.global.common.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class BaseIdItem {
    private long id;

    public static BaseIdItem from(Long id) {
        return BaseIdItem.builder()
            .id(id)
            .build();
    }
}
