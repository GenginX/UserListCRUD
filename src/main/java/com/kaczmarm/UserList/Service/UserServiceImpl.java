package com.kaczmarm.UserList.Service;

import com.kaczmarm.UserList.domain.User;
import com.kaczmarm.UserList.dto.CreateUser;
import com.kaczmarm.UserList.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRep;

    public UserServiceImpl(UserRepository userRep) {
        this.userRep = userRep;
    }

    @Override
    public User addUser(CreateUser createUser) {

        if (!passwordValidation(createUser)) {
            throw new RuntimeException("Password validation failed");
        }
        if (loginValidation(createUser)) {
            throw new RuntimeException("User with this login exists");
        }
        User user = User.builder()
                .login(createUser.getLogin())
                .email(createUser.getEmail())
                .name(createUser.getName())
                .status(createUser.getStatus())
                .password(createUser.getPassword())
                .build();

        return userRep.addUser(user);
    }

    private boolean passwordValidation(CreateUser createUser) {
        String passwordFirst = createUser.getPassword();
        String passwordConf = createUser.getPasswordConf();
        if (passwordConf.equals(passwordFirst)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean loginValidation(CreateUser createUser) {
        String loginToCheck = createUser.getLogin();

        return userRep.getUsers()
                .stream()
                .anyMatch(e -> e.getLogin().equals(loginToCheck));

    }
}
