package cn.mobiledaily.repository;

import cn.mobiledaily.domain.mobile.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    List<Attendee> findByServiceTokenAndExhibition_Code(String serviceToken, String exhibitionCode);
}
