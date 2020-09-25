package com.kaczmarm.UserList.repository;

import com.kaczmarm.UserList.domain.Status;
import com.kaczmarm.UserList.domain.User;
import com.kaczmarm.UserList.exceptionhandler.UserException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    private AtomicLong idCounter = new AtomicLong(0);

    public List<User> getUsers() {
        return users;
    }

    public User addUser(User user) {
        user.setId(idCounter.getAndIncrement());
        users.add(user);

        return user;
    }

    public User findUserById(Long id) throws UserException {
        Optional<User> user = users.stream()
                .filter(e -> e.getId().equals(id))
                .findAny();
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserException("User with id: " + id + " doesn't exist");
        }
    }

    public User BanUser(Long id) throws UserException {
        User userById = findUserById(id);
        if (userById == null) {
        } else if (userById.getStatus().equals(Status.BANNED)) {
            throw new UserException("User is already banned");
        }
        userById.setStatus(Status.BANNED);
        return userById;
    }

    public User findUserByLogin(String login) throws UserException {
        Optional<User> user = users.stream()
                .filter(e -> e.getLogin().equals(login))
                .findAny();
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserException("User with login: " + login + " doesn't exist");
        }
    }

    public List<User> getBannedUsers(){
        List<User> collect = users.stream()
                .filter(e -> e.getStatus().equals(Status.BANNED))
                .collect(Collectors.toList());

        return collect;
    }


}
