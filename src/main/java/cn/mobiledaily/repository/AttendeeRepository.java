package cn.mobiledaily.repository;

import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.domain.mobile.Attendee;

import java.util.List;

public interface AttendeeRepository {
    void persist(Attendee attendee);

    List<Attendee> findAll();

    List<Attendee> findByName(String name);

    User findByEmail(String email);
}
