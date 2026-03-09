package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Pair;

public interface PairJpaRepository extends JpaRepository<Pair, Long> {
}
