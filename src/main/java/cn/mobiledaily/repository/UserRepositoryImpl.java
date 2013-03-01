package cn.mobiledaily.repository;

import cn.mobiledaily.domain.User;
import org.apache.commons.collections.CollectionUtils;
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

    @SuppressWarnings("unchecked")
    public List<User> findAll() {

        return em.createQuery("from User u").getResultList();
    }

    public User findByName(String name) {

        TypedQuery<User> query = em.createQuery("select u from User u where userName =:name", User.class);
        query.setParameter("name", name);
//        @SuppressWarnings("unchecked")
        List<User> users = query.getResultList();
        return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
    }
}
