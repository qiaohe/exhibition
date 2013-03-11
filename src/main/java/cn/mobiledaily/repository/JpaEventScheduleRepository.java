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
}
