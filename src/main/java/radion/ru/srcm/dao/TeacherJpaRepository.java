package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Teacher;

public interface TeacherJpaRepository extends JpaRepository<Teacher, Long> {
}
