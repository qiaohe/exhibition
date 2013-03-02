package cn.mobiledaily.common;

import cn.mobiledaily.domain.User;
import cn.mobiledaily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/1/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RepositoryUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * get user by email
     * @param email email
     * @return User
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return user;
    }
}
