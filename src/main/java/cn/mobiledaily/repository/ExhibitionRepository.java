package cn.mobiledaily.repository;

import cn.mobiledaily.domain.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    Exhibition findByCode(String code);
}
