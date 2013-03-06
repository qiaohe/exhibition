package cn.mobiledaily.repository;

import cn.mobiledaily.domain.mobile.Attendee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "attendeeRepository")
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
    public Attendee findById(Long id) {
        return em.find(Attendee.class, id);
    }
}
