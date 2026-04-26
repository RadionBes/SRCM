package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Room;

/**
 * Репозиторий для работы с сущностью {@link Room} (аудитория/кабинет).
 * <p>
 * Предоставляет стандартные методы для управления аудиториями.
 * При необходимости можно добавить поиск по номеру или корпусу.
 * </p>
 *
 * @see Room
 * @see JpaRepository
 */
public interface RoomJpaRepository extends JpaRepository<Room, Long> {
}
