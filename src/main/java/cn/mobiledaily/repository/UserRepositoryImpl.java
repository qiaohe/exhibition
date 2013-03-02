package cn.mobiledaily.repository;

import cn.mobiledaily.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    public List<User> findByName(String name) {
        TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }
}
