package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Subject;

import java.util.List;
import java.util.Optional;


/**
 * Репозиторий для работы с сущностью {@link Subject} (учебный предмет).
 * <p>
 * Предоставляет метод для поиска предметов по идентификатору с жадной загрузкой
 * связанной сущности {@code group} (группа, для которой читается предмет).
 * </p>
 *
 * @see Subject
 * @see JpaRepository
 */
public interface SubjectJpaRepository extends JpaRepository<Subject, Long> {

    /**
     * Находит список предметов по их идентификатору с загрузкой связанной группы.
     * <p>
     * <b>Внимание:</b> текущая сигнатура метода нестандартна – обычно ожидается
     * {@link Optional}{@code <Subject> findById(Long id)}. Если метод действительно
     * возвращает список, то, вероятно, предполагается поиск по нескольким id
     * или это ошибка. Рекомендуется уточнить логику или переименовать в
     * {@code findAllByIdIn(List<Long> ids)}.
     * </p>
     *
     * @param id идентификатор предмета (может быть {@code null} – вернёт пустой список)
     * @return список предметов с загруженной группой (возможно, пустой)
     */
    @EntityGraph(attributePaths = {"group"})
    List<Subject> findAllById(Long id);
}
