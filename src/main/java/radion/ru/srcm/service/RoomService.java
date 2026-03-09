package radion.ru.srcm.service;

import radion.ru.srcm.entity.Room;

import java.util.List;

public interface RoomService {
    void sync();

    List<Room> getAll();
}
