package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Teacher;

/**
 * Репозиторий для работы с сущностью {@link Teacher} (преподаватель).
 * <p>
 * При необходимости можно добавить поиск по ФИО, кафедре или стажу.
 * </p>
 *
 * @see Teacher
 * @see JpaRepository
 */
public interface TeacherJpaRepository extends JpaRepository<Teacher, Long> {
}
