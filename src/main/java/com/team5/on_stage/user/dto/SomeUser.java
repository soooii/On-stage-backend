package com.team5.on_stage.user.dto;

import com.team5.on_stage.user.entity.OAuth2Domain;
import com.team5.on_stage.user.entity.Role;
import com.team5.on_stage.user.entity.Verified;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SomeUser {

    private String username;

    private String nickname;

    private String description;

    private String profileImage;

    private Verified verified;

    private Role role;

    private OAuth2Domain oAuth2Domain;

    private LocalDateTime createdAt;

    private LocalDateTime deactivatedAt;
}
