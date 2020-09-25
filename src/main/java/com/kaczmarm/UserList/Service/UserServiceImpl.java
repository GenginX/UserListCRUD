package com.kaczmarm.UserList.Service;

import com.kaczmarm.UserList.domain.Status;
import com.kaczmarm.UserList.domain.User;
import com.kaczmarm.UserList.dto.CreateUser;
import com.kaczmarm.UserList.exceptionhandler.UserException;
import com.kaczmarm.UserList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRep;

    public UserServiceImpl(UserRepository userRep) {
        this.userRep = userRep;
    }

    @Override
    public User addUser(CreateUser createUser) throws UserException {

        if (!passwordValidation(createUser)) {
            throw new UserException("Password validation failed");
        }
        if (loginValidation(createUser)) {
            throw new UserException("User with this login exists");
        }
        User user = User.builder()
                .login(createUser.getLogin())
                .email(createUser.getEmail())
                .name(createUser.getName())
                .status(Status.ACTIVE)
                .build();

        return userRep.addUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userRep.getUsers();
    }


    @Override
    public User banUser(Long id) throws UserException {
        return userRep.BanUser(id);
    }

    @Override
    public User findUserByLogin(String login) throws UserException {
        return userRep.findUserByLogin(login);
    }

    @Override
    public List<User> getBannedUsers() {
        return userRep.getBannedUsers();
    }

    private boolean passwordValidation(CreateUser createUser) {
        String passwordFirst = createUser.getPassword();
        String passwordConf = createUser.getPasswordConf();

        return passwordConf.equals(passwordFirst);
    }

    private boolean loginValidation(CreateUser createUser) {
        String loginToCheck = createUser.getLogin();

        return userRep.getUsers()
                .stream()
                .anyMatch(e -> e.getLogin().equals(loginToCheck));

    }

}
