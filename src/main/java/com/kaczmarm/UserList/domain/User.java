package com.kaczmarm.UserList.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class User {

    @Setter
    private Long id;

    private String name;

    private String login;

    private String email;

    private Status status;

    private String password;
}
