package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "exhibitionRepository")
public class JpaExhibitionRepository implements ExhibitionRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Exhibition exhibition) {
        em.merge(exhibition);
    }

    @Override
    public List<Exhibition> findAll() {
        return em.createNamedQuery("Exhibition.findAll", Exhibition.class).getResultList();
    }

    @Override
    public Exhibition findById(Long exhibitionId) {
        List<Exhibition> ebs = em.createNamedQuery("Exhibition.findById", Exhibition.class)
                .setParameter("id", exhibitionId)
                .getResultList();
        return ebs.size() > 0 ? ebs.get(0) : null;
    }

    @Override
    public Exhibition findByCode(String code) {
        List<Exhibition> exhibitions = em.createNamedQuery("Exhibition.findByCode", Exhibition.class)
                .setParameter("code", code)
                .getResultList();
        return exhibitions.size() > 0 ?exhibitions.get(0) : null;
    }
}
