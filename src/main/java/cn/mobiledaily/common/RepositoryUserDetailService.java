package cn.mobiledaily.common;

import cn.mobiledaily.domain.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RepositoryUserDetailService implements UserDetailsService {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mongoOperations.findOne(Query.query(Criteria.where("username").is(username)), User.class);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
