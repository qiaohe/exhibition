package cn.mobiledaily.service;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.PushMessage;
import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.Pusher;
import cn.mobiledaily.exception.EntityNotFoundException;
import cn.mobiledaily.repository.AttendeeRepository;
import cn.mobiledaily.repository.ExhibitionRepository;
import cn.mobiledaily.repository.PushMessageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cn.mobiledaily.exception.EntityNotFoundException.EXHIBITION_ERROR_FORMAT;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/14/13
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
@Service(value = "pushMessageService")
public class PushMessageServiceImpl implements PushMessageService {
    @Autowired
    private ExhibitionRepository exhibitionRepository;
    @Autowired
    private PushMessageRepository pushMessageRepository;
    @Autowired
    private AttendeeRepository attendeeRepository;
    @Autowired
    private Pusher pusher;

    @Override
    public void pushMessage(String exhibitionCode, PushMessage message) {
        Exhibition exhibition = exhibitionRepository.findByCode(exhibitionCode);
        if (exhibition == null)
            throw new EntityNotFoundException(String.format(EXHIBITION_ERROR_FORMAT, exhibitionCode));
        message.setExhibition(exhibition);
        pusher.push(message.getBody(), getRecipients());
        pushMessageRepository.persist(message);
    }

    @Override
    public List<PushMessage> getMessages(String exhibitionCode) {
        return pushMessageRepository.findByCode(exhibitionCode);
    }

    private List<String> getRecipients() {
        Set<String> result = new HashSet<>();
        for (Attendee ad : attendeeRepository.findAll()) {
            result.add(ad.getServiceToken());
        }
        return new ArrayList<>(result);
    }
}
