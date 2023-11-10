package com.projpolice.domain.user.dto;

import java.util.Objects;

import com.projpolice.domain.user.domain.rdb.User;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserIdNameItem that = (UserIdNameItem)o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
