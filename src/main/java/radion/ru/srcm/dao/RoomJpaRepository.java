package radion.ru.srcm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Room;

public interface RoomJpaRepository extends JpaRepository<Room, Long> {
}
