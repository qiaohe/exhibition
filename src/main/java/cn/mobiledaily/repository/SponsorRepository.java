package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    List<Sponsor> findByExhibitionCode(String code);
}
