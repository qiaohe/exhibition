package cn.mobiledaily.service;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.PushMessage;
import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.pushnotification.AndroidPusher;
import cn.mobiledaily.domain.mobile.pushnotification.IosPusher;
import cn.mobiledaily.domain.mobile.pushnotification.MobilePlatform;
import cn.mobiledaily.exception.EntityNotFoundException;
import cn.mobiledaily.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.mobiledaily.exception.EntityNotFoundException.EXHIBITION_ERROR_FORMAT;

@Transactional(readOnly = true)
@Service(value = "pushMessageService")
public class PushMessageServiceImpl implements PushMessageService {
    @Autowired
    private ExhibitionRepository exhibitionRepository;
    @Autowired
    private IosPusher iosPusher;
    @Autowired
    private AndroidPusher androidPusher;

    @Override
    @Transactional
    public void pushMessage(String exhibitionCode, PushMessage message) {
        Exhibition exhibition = exhibitionRepository.findByCode(exhibitionCode);
        if (exhibition == null)
            throw new EntityNotFoundException(String.format(EXHIBITION_ERROR_FORMAT, exhibitionCode));
        message.setExhibition(exhibition);
        push(message, exhibition);
        exhibitionRepository.save(message);
    }

    @Override
    @Transactional
    public List<PushMessage> getMessages(String exhibitionCode) {
        Exhibition exhibition = exhibitionRepository.findByCode(exhibitionCode);
        return exhibitionRepository.findContents(exhibition, PushMessage.class);
    }

    private void push(PushMessage message, Exhibition exhibition) {
        for (Attendee ad : exhibitionRepository.findContents(exhibition, Attendee.class)) {
            try {
                if (ad.getMobilePlatform().equals(MobilePlatform.ANDROID)) {
                    androidPusher.push(message.getBody(), ad.getServiceToken());
                } else if (ad.getMobilePlatform().equals(MobilePlatform.IOS)) {
                    iosPusher.push(message.getBody(), ad.getServiceToken());
                }
            } catch (Exception ignore) {
            }
        }
    }
}
