package cn.mobiledaily.repository;

import cn.mobiledaily.domain.PushMessage;
import cn.mobiledaily.domain.Speaker;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/14/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "pushMessageRepository")
public class PushMessageRepositoryImpl implements PushMessageRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PushMessage> findByCode(String code) {
        return em.createNamedQuery("PushMessage.findByCode", PushMessage.class)
                .setParameter("code", code)
                .getResultList();
    }

    @Override
    public void persist(PushMessage pushMessage) {
        em.persist(pushMessage);
    }
}
