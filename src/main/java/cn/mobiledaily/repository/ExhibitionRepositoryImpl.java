package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/6/13
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "exhibitionRepository")
public class ExhibitionRepositoryImpl implements ExhibitionRepository {
    @PersistenceContext
    private EntityManager em;


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
}
