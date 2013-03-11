package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibitor;

import java.util.List;

public interface ExhibitorRepository {
    List<Exhibitor> findByCode(String code);
}
