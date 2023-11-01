package com.projpolice.domain.user.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserIdNameImgItem extends UserIdNameItem {
    private String image;
}
