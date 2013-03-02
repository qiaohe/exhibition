package cn.mobiledaily.service;

import cn.mobiledaily.domain.Role;
import cn.mobiledaily.domain.User;
import cn.mobiledaily.exception.ExistedEmailException;
import cn.mobiledaily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User register(String username, String password, String email) throws ExistedEmailException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new ExistedEmailException(email);
        }
        user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        user.setAuthority(Role.USER.getAuthority());
        userRepository.persist(user);
        return user;
    }

    @Transactional
    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.persist(user);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public User changeUserName(String email, String newUserName) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setUsername(newUserName);
            userRepository.persist(user);
        }
        return user;
    }

    @Transactional
    @Override
    public User changeEmail(String email, String newEmail) throws ExistedEmailException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            User check = userRepository.findByEmail(newEmail);
            if (check != user) {
                throw new ExistedEmailException(newEmail);
            }
            user.setEmail(newEmail);
            userRepository.persist(user);
        }
        return user;
    }

    @Transactional
    @Override
    public User changeAuthority(String email, String authority) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setAuthority(authority);
            userRepository.persist(user);
        }
        return user;
    }
}
