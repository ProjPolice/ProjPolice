package com.projpolice.domain.user.dto;

import com.projpolice.domain.user.domain.rdb.User;
import com.projpolice.global.storage.base.StorageProvider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserIdNameImgItem extends UserIdNameItem {
    private String image;

    public static UserIdNameImgItem from(User user) {
        return UserIdNameImgItem.builder()
            .id(user.getId())
            .name(user.getName())
            .image(user.getImage() == null ? null :
                String.format("%s%s", StorageProvider.getPreAuthenticatedUrl(), user.getImage()))
            .build();
    }
}
