package com.kaczmarm.UserList.repository;

import com.kaczmarm.UserList.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    private AtomicLong idCounter = new AtomicLong(0);

    public List<User> getUsers(){
        return users;
    }

    public User addUser(User user){
        user.setId(idCounter.getAndIncrement());
        users.add(user);

        return user;
    }

}
