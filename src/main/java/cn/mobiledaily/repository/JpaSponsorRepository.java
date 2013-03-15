package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Sponsor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaSponsorRepository implements SponsorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Sponsor> findByCode(String code) {
        return em.createNamedQuery("Sponsor.findByCode", Sponsor.class)
                .setParameter("code", code)
                .getResultList();
    }

    @Override
    public void persist(Sponsor sponsor) {
        em.merge(sponsor);
    }

    @Override
    public Sponsor findById(long id) {
        List<Sponsor> list = em.createNamedQuery("Sponsor.findById", Sponsor.class)
                .setParameter("id", id)
                .getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void remove(Sponsor sponsor) {
        em.remove(em.merge(sponsor));
    }
}
