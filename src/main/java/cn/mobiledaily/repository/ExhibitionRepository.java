package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.ExhibitionContent;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ExhibitionRepository {
    void save(Exhibition exhibition);
    void save(ExhibitionContent content);
    Exhibition findByCode(String code);
    List<Exhibition> findAll();
    Exhibition findById(String id);
    void delete(Exhibition exhibition);
    void delete(ExhibitionContent content);
    <T extends ExhibitionContent> List<T> findContents(Exhibition exhibition, Class<T> type);
    <T extends ExhibitionContent> List<T> findContents(Exhibition exhibition, Class<T> type, Sort sort);
    <T extends ExhibitionContent> T findContentById(Exhibition exhibition, String id, Class<T> type);
    List<Attendee> findAttendeesByServiceToken(Exhibition exhibition, String token);
}
