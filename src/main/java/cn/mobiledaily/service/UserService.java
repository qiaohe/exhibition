package cn.mobiledaily.service;

import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.exception.EmailExistsException;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User findByEmail(String email);

    List<User> findByUsername(String username);

    void register(String username, String password, String email) throws EmailExistsException;

    boolean changePassword(String email, String oldPassword, String newPassword);

    void changeUsername(String email, String newUsername);

    User changeEmail(String email, String newEmail) throws EmailExistsException;

    User changeAuthority(String email, String authority);

}
