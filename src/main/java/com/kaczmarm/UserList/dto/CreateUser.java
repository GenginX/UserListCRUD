package com.kaczmarm.UserList.dto;

import com.kaczmarm.UserList.domain.Status;
import lombok.Data;

@Data
public class CreateUser {

    private String name;

    private String login;

    private String password;

    private String passwordConf;

    private String email;

    private Status Status;

}
