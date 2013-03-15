package cn.mobiledaily.repository;

import cn.mobiledaily.domain.EventSchedule;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaEventScheduleRepository implements EventScheduleRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<EventSchedule> findByCode(String code) {
        return em.createNamedQuery("EventSchedule.findByCode", EventSchedule.class)
                .setParameter("code", code)
                .getResultList();
    }

    @Override
    public void persist(EventSchedule eventSchedule) {
        em.merge(eventSchedule);
    }

    @Override
    public EventSchedule findById(long id) {
        List<EventSchedule> list = em.createNamedQuery("EventSchedule.findById", EventSchedule.class)
                .setParameter("id", id)
                .getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void remove(EventSchedule eventSchedule) {
        em.remove(em.merge(eventSchedule));
    }
}
