package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.entity.Student;


/**
 * Репозиторий для работы с сущностью {@link Student} (студент).
 * <p>
 * Содержит метод проверки существования студента по группе и полному имени.
 * </p>
 *
 * @see Student
 * @see Group
 * @see JpaRepository
 */
public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    /**
     * Проверяет, существует ли студент с указанным полным именем в заданной группе.
     * <p>
     * Используется для предотвращения дублирования студентов при импорте данных.
     * </p>
     *
     * @param group    группа, к которой принадлежит студент (не {@code null})
     * @param fullName полное имя студента (например, "Иванов Иван Иванович")
     * @return {@code true}, если студент уже существует в группе, иначе {@code false}
     */
    boolean existsStudentByGroupAndFullName(Group group, String fullName);
}
