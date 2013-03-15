package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibitor;

import java.util.List;

public interface ExhibitorRepository {
    List<Exhibitor> findByCode(String code);

    Exhibitor findById(long id);

    void persist(Exhibitor exhibitor);

    void remove(Exhibitor exhibitor);
}
