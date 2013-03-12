package cn.mobiledaily.service;

import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.exception.UsernameExistsException;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User findByUsername(String username);

    void register(String username, String password, String email) throws UsernameExistsException;

    boolean changePassword(String username, String oldPassword, String newPassword);

    void changeUsername(String username, String newUsername) throws UsernameExistsException;

    User changeEmail(String username, String newEmail);

    User changeAuthority(String username, String authority);

}
