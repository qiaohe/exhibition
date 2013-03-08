package cn.mobiledaily.service;

import cn.mobiledaily.domain.Exhibition;

import java.util.List;

public interface ExhibitionService {
    void persist(Exhibition exhibition);

    List<Exhibition> findAll();

    Exhibition findByCode(String code);
}
