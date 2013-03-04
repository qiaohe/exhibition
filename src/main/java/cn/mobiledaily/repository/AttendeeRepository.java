package cn.mobiledaily.repository;

import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.domain.mobile.Attendee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/4/13
 * Time: 10:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AttendeeRepository {
    void persist(Attendee attendee);

    List<Attendee> findAll();

    List<Attendee> findByName(String name);

    User findByEmail(String email);
}
