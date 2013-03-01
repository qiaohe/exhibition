package cn.mobiledaily.service;

import cn.mobiledaily.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    List<User> loadUserByUsername(String username) throws UsernameNotFoundException;

    void persist(User user) throws Exception;

    List<User> getUsers();
}
