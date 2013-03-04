package cn.mobiledaily.service;

import cn.mobiledaily.domain.identity.Role;
import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.exception.EmailExistsException;
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

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public List<User> findByUsername(String username) {
        return null;

    }

    @Transactional
    @Override
    public void register(String username, String password, String email) throws EmailExistsException {
        if (userRepository.findByEmail(email) != null) {
            throw new EmailExistsException(email);
        }
        User user = new User(username, passwordEncoder.encode(password), email, Role.USER.getAuthority());
        userRepository.persist(user);
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
    public void changeUsername(String email, String newUsername) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setUsername(newUsername);
            userRepository.persist(user);
        }
    }

    @Transactional
    @Override
    public User changeEmail(String email, String newEmail) throws EmailExistsException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            User check = userRepository.findByEmail(newEmail);
            if (check != null && check != user) {
                throw new EmailExistsException(newEmail);
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
