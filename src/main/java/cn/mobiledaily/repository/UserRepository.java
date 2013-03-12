package cn.mobiledaily.repository;

import cn.mobiledaily.domain.identity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 2/28/13
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository {
    void persist(User user);

    List<User> findAll();

    User findByName(String name);
}
