package com.kaczmarm.UserList.Service;

import com.kaczmarm.UserList.domain.User;
import com.kaczmarm.UserList.dto.CreateUser;
import com.kaczmarm.UserList.exceptionhandler.UserException;

import java.util.List;

public interface UserService {

    public User addUser(CreateUser createUser) throws UserException;

    public List<User> getUsers();

    public User banUser(Long id) throws UserException;

    public User findUserByLogin(String login) throws UserException;

    public List<User> getBannedUsers();

}
