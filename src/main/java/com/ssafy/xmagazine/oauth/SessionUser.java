package com.ssafy.xmagazine.oauth;

import java.io.Serializable;

import com.ssafy.xmagazine.domain.user.UserDto;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(UserDto userDto) {
        this.name = userDto.getUsername();
        this.email = userDto.getEmail();
        this.picture = userDto.getProfilePicture();
    }
}
