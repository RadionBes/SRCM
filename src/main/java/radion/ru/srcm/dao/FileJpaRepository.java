package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.File;

public interface FileJpaRepository extends JpaRepository<File, Long> {
}
