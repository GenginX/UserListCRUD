package com.kaczmarm.UserList.Service;

import com.kaczmarm.UserList.domain.User;
import com.kaczmarm.UserList.dto.CreateUser;

public interface UserService {

    public User addUser(CreateUser createUser);

}
