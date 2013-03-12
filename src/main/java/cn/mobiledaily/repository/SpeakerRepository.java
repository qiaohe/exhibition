package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findByCode(String code);

    void persist(Speaker speaker);
}
