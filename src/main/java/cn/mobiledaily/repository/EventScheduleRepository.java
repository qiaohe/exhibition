package cn.mobiledaily.repository;

import cn.mobiledaily.domain.EventSchedule;

import java.util.List;

public interface EventScheduleRepository {
    List<EventSchedule> findByCode(String code);
}
