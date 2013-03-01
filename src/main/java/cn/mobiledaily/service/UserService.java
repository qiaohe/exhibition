package cn.mobiledaily.service;

import cn.mobiledaily.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User register(String userName, String password, String email);

    boolean changePassword(String email, String oldPassword, String newPassword);

    User changeUserName(String email, String newUserName);

    User changeEmail(String email, String newEmail);

    User changeAuthority(String email, String authority);

}
