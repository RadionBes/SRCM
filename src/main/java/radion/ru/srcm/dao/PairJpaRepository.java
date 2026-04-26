package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Pair;
/**
 * Репозиторий для работы с сущностью {@link Pair}.
 * <p>
 * Используется для доступа к расписанию пар. При необходимости можно добавить
 * методы поиска по времени, аудитории, преподавателю и т.д.
 * </p>
 *
 * @see Pair
 * @see JpaRepository
 */
public interface PairJpaRepository extends JpaRepository<Pair, Long> {
}
