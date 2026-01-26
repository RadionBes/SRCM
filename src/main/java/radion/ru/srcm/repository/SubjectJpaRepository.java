package radion.ru.srcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Subject;

import java.util.List;

public interface SubjectJpaRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllById(Long id);
}
