package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Week;


/**
 * Репозиторий для работы с сущностью {@link Week} (учебная неделя).
 * <p>
 * Для управления неделями. Неделей считается начальная дата недели с понедельника.
 * </p>
 *
 * @see Week
 * @see JpaRepository
 */
public interface WeekJpaRepository extends JpaRepository<Week, Long> {

}
