package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.TimetableTeacher;

public interface TimetableTeacherJpaRepository extends JpaRepository<TimetableTeacher, Long> {
}
