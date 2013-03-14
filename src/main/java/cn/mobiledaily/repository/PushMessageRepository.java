package cn.mobiledaily.repository;

import cn.mobiledaily.domain.PushMessage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/14/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PushMessageRepository {
    List<PushMessage> findByCode(String code);

    void persist(PushMessage pushMessage);
}
