package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibition;

import java.util.List;

public interface ExhibitionRepository {
    void persist(Exhibition exhibition);

    List<Exhibition> findAll();

    Exhibition findById(Long exhibitionId);

    Exhibition findByCode(String code);

    void remove(Exhibition exhibition);
}
