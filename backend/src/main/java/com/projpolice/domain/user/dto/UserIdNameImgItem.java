package com.projpolice.domain.user.dto;

import com.projpolice.domain.user.domain.User;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserIdNameImgItem extends UserIdNameItem {
    private String image;

    public static UserIdNameImgItem from(User user) {
        return UserIdNameImgItem.builder()
            .id(user.getId())
            .name(user.getName())
            .image(user.getImage())
            .build();
    }
}
