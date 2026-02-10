package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Group;

public interface GroupJpaRepository extends JpaRepository<Group, Long> {
    boolean existsGroupByKey(String key);

    Group findGroupByKey(String key);
}
