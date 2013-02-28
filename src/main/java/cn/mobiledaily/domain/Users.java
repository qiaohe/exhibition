package cn.mobiledaily.domain;

import cn.mobiledaily.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class Users {
    @PersistenceContext
    private EntityManager em;

    public void persist(User user) {
        em.persist(user);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u").getResultList();
    }

    public User findByName(String name) {
        Query query = em.createQuery("select u from User u where name=:name");
        query.setParameter("name", name);
        List<User> users = query.getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }
}
