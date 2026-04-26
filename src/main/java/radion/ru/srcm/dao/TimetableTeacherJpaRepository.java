package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.TimetableTeacher;

/**
 * Репозиторий для работы с сущностью {@link TimetableTeacher} (расписание преподавателя).
 * <p>
 * Используется для хранения и извлечения расписания, связанного с конкретным преподавателем.
 * При необходимости можно добавить методы поиска по преподавателю, неделе, паре и т.д.
 * </p>
 *
 * @see TimetableTeacher
 * @see JpaRepository
 */
public interface TimetableTeacherJpaRepository extends JpaRepository<TimetableTeacher, Long> {

}
