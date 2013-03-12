package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Sponsor;

import java.util.List;

public interface SponsorRepository {
    List<Sponsor> findByCode(String code);

    void persist(Sponsor sponsor);
}
