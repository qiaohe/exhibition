package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ExhibitorRepository extends JpaRepository<Exhibitor, Long> {
    List<Exhibitor> findByExhibitionCode(String code);
}
