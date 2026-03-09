package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Subject;

import java.util.List;

public interface SubjectJpaRepository extends JpaRepository<Subject, Long> {
    @EntityGraph(attributePaths = {"group"})
    List<Subject> findAllById(Long id);
}
