package cn.mobiledaily.service;

import cn.mobiledaily.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User register(String userName, String password, String email) {
        return null;

    }

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        return false;

    }

    @Override
    public User changeUserName(String email, String newUserName) {
        return null;

    }

    @Override
    public User changeEmail(String email, String newEmail) {
        return null;

    }

    @Override
    public User changeAuthority(String email, String authority) {
        return null;

    }
}
