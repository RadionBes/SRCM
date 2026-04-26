package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.File;

/**
 * Репозиторий для работы с сущностью {@link File}.
 * <p>
 * Предоставляет стандартные методы CRUD (создание, чтение, обновление, удаление)
 * и дополнительные запросы, наследуемые от {@link JpaRepository}.
 * </p>
 *
 * @see File
 * @see JpaRepository
 */
public interface FileJpaRepository extends JpaRepository<File, Long> {
}
