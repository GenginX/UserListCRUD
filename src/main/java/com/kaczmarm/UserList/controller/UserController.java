package com.kaczmarm.UserList.controller;

import com.kaczmarm.UserList.Service.UserService;
import com.kaczmarm.UserList.domain.User;
import com.kaczmarm.UserList.dto.CreateUser;
import com.kaczmarm.UserList.exceptionhandler.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody CreateUser createUser) throws UserException {
        User user = userService.addUser(createUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/ban/{id}")
    public ResponseEntity<User> banUser(@PathVariable ("id") Long id) throws UserException {
        User user = userService.banUser(id);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/login")
    public ResponseEntity<User> findUserByLogin(@RequestParam String login) throws UserException {
        User userByLogin = userService.findUserByLogin(login);
        return new ResponseEntity<>(userByLogin, HttpStatus.FOUND);
    }

    @GetMapping("/banned_users")
    public ResponseEntity<List<User>> getBannedUsers(){
        List<User> bannedUsers = userService.getBannedUsers();
        return new ResponseEntity<>(bannedUsers, HttpStatus.FOUND);
    }

}
