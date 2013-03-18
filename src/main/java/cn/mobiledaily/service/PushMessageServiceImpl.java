package cn.mobiledaily.service;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.PushMessage;
import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.Pusher;
import cn.mobiledaily.exception.EntityNotFoundException;
import cn.mobiledaily.repository.AttendeeRepository;
import cn.mobiledaily.repository.ExhibitionRepository;
import cn.mobiledaily.repository.PushMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cn.mobiledaily.exception.EntityNotFoundException.EXHIBITION_ERROR_FORMAT;

@Transactional(readOnly = true)
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
    @Transactional
    public void pushMessage(String exhibitionCode, PushMessage message) {
        Exhibition exhibition = exhibitionRepository.findByCode(exhibitionCode);
        if (exhibition == null)
            throw new EntityNotFoundException(String.format(EXHIBITION_ERROR_FORMAT, exhibitionCode));
        message.setExhibition(exhibition);
        pusher.push(message.getBody(), getRecipients());
        pushMessageRepository.save(message);
    }

    @Override
    @Transactional
    public List<PushMessage> getMessages(String exhibitionCode) {
        return pushMessageRepository.findByExhibitionCode(exhibitionCode);
    }

    private List<String> getRecipients() {
        Set<String> result = new HashSet<>();
        for (Attendee ad : attendeeRepository.findAll()) {
            result.add(ad.getServiceToken());
        }
        return new ArrayList<>(result);
    }
}
