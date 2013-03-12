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
    public List<Exhibitor> findByCode(String code) {
        return em.createNamedQuery("Exhibitor.findByCode", Exhibitor.class)
                .setParameter("code", code)
                .getResultList();
    }

    @Override
    public void persist(Exhibitor exhibitor) {
        em.persist(exhibitor);
    }
}
