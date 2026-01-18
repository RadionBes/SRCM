package radion.ru.srcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Group;

public interface GroupJpaRepository extends JpaRepository<Group, Long> {
    boolean existsGroupByKey(String key);
}
