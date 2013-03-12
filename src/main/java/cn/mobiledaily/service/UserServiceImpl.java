package cn.mobiledaily.service;

import cn.mobiledaily.domain.identity.Role;
import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.exception.UsernameExistsException;
import cn.mobiledaily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
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

    @Override
    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Transactional
    @Override
    public void register(String username, String password, String email) throws UsernameExistsException {
        if (userRepository.findByName(username) != null) {
            throw new UsernameExistsException(username);
        }
        User user = new User(username, passwordEncoder.encode(password), email, Role.USER.getAuthority());
        userRepository.persist(user);
    }

    @Transactional
    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByName(username);
        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.persist(user);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void changeUsername(String username, String newUsername) throws UsernameExistsException {
        User user = userRepository.findByName(username);
        if (user != null) {
            User check = userRepository.findByName(newUsername);
            if (check != null && check != user) {
                throw new UsernameExistsException(newUsername);
            }
            user.setUsername(newUsername);
            userRepository.persist(user);
        }
    }

    @Transactional
    @Override
    public User changeEmail(String username, String newEmail) {
        User user = userRepository.findByName(username);
        if (user != null) {
            user.setEmail(newEmail);
            userRepository.persist(user);
        }
        return user;
    }

    @Transactional
    @Override
    public User changeAuthority(String username, String authority) {
        User user = userRepository.findByName(username);
        if (user != null) {
            user.setAuthority(authority);
            userRepository.persist(user);
        }
        return user;
    }
}
