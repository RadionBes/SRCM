package radion.ru.srcm.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Group;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link Group} (учебная группа).
 * <p>
 * Помимо стандартных методов JPA, предоставляет поиск по уникальному ключу группы
 * с жадной загрузкой связанных коллекций {@code studentList}, {@code files},
 * {@code timetables} и {@code subjects} через {@link EntityGraph}.
 * </p>
 *
 * @see Group
 * @see JpaRepository
 */
public interface GroupJpaRepository extends JpaRepository<Group, Long> {
    /**
     * Проверяет, существует ли группа с заданным ключом.
     *
     * @param key уникальный строковый идентификатор группы (например, "234")
     * @return {@code true}, если группа существует, иначе {@code false}
     */
    boolean existsGroupByKey(String key);

    /**
     * Находит группу по её ключу, одновременно загружая связанные сущности:
     * <ul>
     *     <li>список студентов ({@code studentList})</li>
     *     <li>список файлов ({@code files})</li>
     *     <li>списание расписаний ({@code timetables})</li>
     *     <li>список предметов ({@code subjects})</li>
     * </ul>
     * Использование {@link EntityGraph} позволяет избежать проблемы N+1 запроса.
     *
     * @param key ключ группы (не должен быть {@code null})
     * @return {@link Optional}, содержащий группу, если найдена, иначе пустой Optional
     */
    @EntityGraph(attributePaths = {"studentList", "files", "timetables", "subjects"})
    Optional<Group> findGroupByKey(String key);

    /**
     * Переопределяет стандартный метод {@link JpaRepository#findAll()}
     * с добавлением жадной загрузки всех связанных коллекций (см. {@link #findGroupByKey}).
     *
     * @return непустой список всех групп (может быть пустым, если нет ни одной группы)
     */
    @NotNull
    @Override
    @EntityGraph(attributePaths = {"studentList", "files", "timetables", "subjects"})
    List<Group> findAll();
}
