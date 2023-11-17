package com.projpolice.domain.user.dto;

import com.projpolice.domain.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserIdNameItem {

    private long id;

    private String name;

    public static UserIdNameItem from(User user) {
        return UserIdNameItem.builder()
            .id(user.getId())
            .name(user.getName())
            .name(user.getName())
            .build();
    }
}
