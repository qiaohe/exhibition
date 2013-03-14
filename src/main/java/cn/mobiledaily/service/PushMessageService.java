package cn.mobiledaily.service;

import cn.mobiledaily.domain.PushMessage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/14/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PushMessageService {
    void pushMessage(String exhibitionCode, PushMessage message);

    List<PushMessage> getMessages(String exhibitionCode);
}
