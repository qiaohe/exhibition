package cn.mobiledaily.service;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.News;
import cn.mobiledaily.domain.mobile.ExhibitionContent;
import cn.mobiledaily.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("exhibitionService")
@Transactional(readOnly = true)
public class ExhibitionServiceImpl implements ExhibitionService {
    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Override
    @Transactional
    public void save(Exhibition exhibition) {
        exhibitionRepository.save(exhibition);
    }

    @Override
    @Transactional
    public void save(ExhibitionContent content) {
        Exhibition exhibition = content.getExhibition();
        exhibition.setUpdatedAt(new Date());
        exhibitionRepository.save(content);
        exhibitionRepository.save(exhibition);
    }

    @Override
    public List<Exhibition> findAll() {
        return exhibitionRepository.findAll();
    }

    @Override
    public Exhibition findByCode(String code) {
        return exhibitionRepository.findByCode(code);
    }

    @Override
    public Exhibition findById(String id) {
        return exhibitionRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Exhibition exhibition) {
        exhibitionRepository.delete(exhibition);
    }

    @Override
    public <T extends ExhibitionContent> List<T> findContents(String exhibitionCode, Class<T> type, Sort sort) {
        try {
            Exhibition exhibition = findByCode(exhibitionCode);
            return exhibitionRepository.findContents(exhibition, type, sort);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends ExhibitionContent> List<T> findContents(String exhibitionCode, Class<T> type) {
        return findContents(exhibitionCode, type, null);
    }

    @Override
    public <T extends ExhibitionContent> T findContentById(String exhibitionCode, String id, Class<T> type) {
        try {
            Exhibition exhibition = findByCode(exhibitionCode);
            return exhibitionRepository.findContentById(exhibition, id, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<News> findNews(String exhibitionCode, long from, int size) {
        try {
            Exhibition exhibition = findByCode(exhibitionCode);
            return exhibitionRepository.findNews(exhibition, from, size);
        } catch (Exception e) {
            return new ArrayList<>(0);
        }
    }

    @Override
    @Transactional
    public void delete(ExhibitionContent content) {
        exhibitionRepository.delete(content);
    }
}
