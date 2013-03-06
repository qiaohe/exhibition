package cn.mobiledaily.repository;

import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.domain.mobile.Attendee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaAttendeeRepository implements AttendeeRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Attendee attendee) {
        em.persist(attendee);
    }

    @Override
    public List<Attendee> findAll() {
        return em.createNamedQuery("Attendee.findAll", Attendee.class).getResultList();
    }

    @Override
    public List<Attendee> findByName(String name) {
        return em.createNamedQuery("Attendee.findByName", Attendee.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
