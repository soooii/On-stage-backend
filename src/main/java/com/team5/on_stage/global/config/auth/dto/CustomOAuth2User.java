package com.team5.on_stage.global.config.auth.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final UserDto userDto;


    // Todo: 도메인에 따라 Attribute의 형태가 다르다. 그래서 획일화하기가 어려움.
    @Override
    public Map<String, Object> getAttributes() {

        return null;
//        return userDto.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return userDto.getRole().name();
            }
        });
        return authorities;
    }

    @Override
    public String getName() {

        return userDto.getName();
    }

    public String getUsername() {

        return userDto.getUsername();
    }

    public String getNickname() {

        return userDto.getNickname();
    }
}
