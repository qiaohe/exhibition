package cn.mobiledaily.repository;

import cn.mobiledaily.domain.identity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository(value = "userRepository")
public class JpaUserRepository implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    public void persist(User user) {
        em.persist(user);
    }

    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    public User findByName(String name) {
        TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
        query.setParameter("name", name);
        List<User> users = query.getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }
}
