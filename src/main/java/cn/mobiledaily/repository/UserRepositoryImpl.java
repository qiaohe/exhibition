package cn.mobiledaily.repository;

import cn.mobiledaily.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 2/28/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "userRepository")
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    public void persist(User user) {
        em.persist(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return em.createQuery("select u from User u").getResultList();
    }

    public User findByName(String name) {
        Query query = em.createQuery("select u from User u where name=:name");
        query.setParameter("name", name);
        @SuppressWarnings("unchecked")
        List<User> users = query.getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }
}
