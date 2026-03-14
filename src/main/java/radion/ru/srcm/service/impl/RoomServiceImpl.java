package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dao.RoomJpaRepository;
import radion.ru.srcm.entity.Room;
import radion.ru.srcm.logging.Loggable;
import radion.ru.srcm.mapper.entity.RoomMapperEntity;
import radion.ru.srcm.service.RoomService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final MapApiCollageService mapApiCollageService;
    private final RoomJpaRepository roomJpaRepository;
    private final RoomMapperEntity roomMapperEntity;

    @Override
    @Loggable(value = "Синхронизация аудиторий", logParams = false)
    public void sync() {
        roomJpaRepository.saveAll(
                roomMapperEntity.toEntity(
                    mapApiCollageService.getListRooms()
                )
        );
    }

    @Override
    @Loggable(value = "Получение всех аудиторий", logParams = false)
    public List<Room> getAll() {
        return roomJpaRepository.findAll();
    }
}
