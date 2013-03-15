package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Speaker;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaSpeakerRepository implements SpeakerRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Speaker> findByCode(String code) {
        return em.createNamedQuery("Speaker.findByCode", Speaker.class)
                .setParameter("code", code)
                .getResultList();
    }

    @Override
    public void persist(Speaker speaker) {
        em.merge(speaker);
    }

    @Override
    public Speaker findById(long id) {
        List<Speaker> list = em.createNamedQuery("Speaker.findById", Speaker.class)
                .setParameter("id", id)
                .getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void remove(Speaker speaker) {
        em.remove(em.merge(speaker));
    }
}
