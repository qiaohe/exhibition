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
}
