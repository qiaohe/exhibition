package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibitor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaExhibitorRepository implements ExhibitorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Exhibitor findById(long id) {
        List<Exhibitor> list = em.createNamedQuery("Exhibitor.findById", Exhibitor.class)
                .setParameter("id", id)
                .getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Exhibitor> findByCode(String code) {
        return em.createNamedQuery("Exhibitor.findByCode", Exhibitor.class)
                .setParameter("code", code)
                .getResultList();
    }

    @Override
    public void persist(Exhibitor exhibitor) {
        em.merge(exhibitor);
    }

    @Override
    public void remove(Exhibitor exhibitor) {
        em.remove(em.merge(exhibitor));
    }
}
