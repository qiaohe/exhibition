package cn.mobiledaily.service;

import cn.mobiledaily.domain.identity.Role;
import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.exception.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return mongoOperations.findAll(User.class);
    }

    @Override
    public User findByUsername(String username) {
        return mongoOperations.findOne(Query.query(Criteria.where("username").is(username)), User.class);
    }

    @Transactional
    @Override
    public void register(String username, String password, String email) throws UsernameExistsException {
        if (findByUsername(username) != null) {
            throw new UsernameExistsException(username);
        }
        User user = new User(username, passwordEncoder.encode(password), email, Role.USER.getAuthority());
        mongoOperations.save(user);
    }

    @Transactional
    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = findByUsername(username);
        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            mongoOperations.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void changeUsername(String username, String newUsername) throws UsernameExistsException {
        User user = findByUsername(username);
        if (user != null) {
            User check = findByUsername(newUsername);
            if (check != null && check != user) {
                throw new UsernameExistsException(newUsername);
            }
            user.setUsername(newUsername);
            mongoOperations.save(user);
        }
    }

    @Transactional
    @Override
    public User changeEmail(String username, String newEmail) {
        User user = findByUsername(username);
        if (user != null) {
            user.setEmail(newEmail);
            mongoOperations.save(user);
        }
        return user;
    }

    @Transactional
    @Override
    public User changeAuthority(String username, String authority) {
        User user = findByUsername(username);
        if (user != null) {
            user.setAuthority(authority);
            mongoOperations.save(user);
        }
        return user;
    }
}
