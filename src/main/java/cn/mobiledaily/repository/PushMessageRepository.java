package cn.mobiledaily.repository;

import cn.mobiledaily.domain.PushMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PushMessageRepository extends JpaRepository<PushMessage, Long> {
    List<PushMessage> findByExhibitionCode(String code);
}
