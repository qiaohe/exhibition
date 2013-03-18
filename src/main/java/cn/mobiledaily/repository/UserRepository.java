package cn.mobiledaily.repository;

import cn.mobiledaily.domain.identity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
