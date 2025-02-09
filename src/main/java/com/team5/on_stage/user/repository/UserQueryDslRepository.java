package com.team5.on_stage.user.repository;

import com.team5.on_stage.user.entity.User;

public interface UserQueryDslRepository {
    User findByUsername(String username);

    User findByNickname(String nickname);

    void softDeleteUserByUsername(String username);

    Boolean existsByNickname(String nickname);
}
