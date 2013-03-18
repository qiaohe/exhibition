package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    List<Speaker> findByExhibitionCode(String code);
}
