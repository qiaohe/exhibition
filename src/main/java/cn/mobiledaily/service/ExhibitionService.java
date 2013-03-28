package cn.mobiledaily.service;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.mobile.ExhibitionContent;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ExhibitionService {
    void save(Exhibition exhibition);

    void save(ExhibitionContent content);

    List<Exhibition> findAll();

    Exhibition findByCode(String code);

    Exhibition findById(String id);

    void delete(Exhibition exhibition);

    <T extends ExhibitionContent> List<T> findContents(String exhibitionCode, Class<T> type);

    <T extends ExhibitionContent> List<T> findContents(String exhibitionCode, Class<T> type, Sort sort);

    <T extends ExhibitionContent> T findContentById(String exhibitionCode, String id, Class<T> type);

    <T extends ExhibitionContent> void delete(ExhibitionContent content);
}
