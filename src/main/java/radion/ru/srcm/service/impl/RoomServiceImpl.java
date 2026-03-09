package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dao.RoomJpaRepository;
import radion.ru.srcm.mapper.entity.RoomMapperEntity;
import radion.ru.srcm.service.RoomService;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final MapApiCollageService mapApiCollageService;
    private final RoomJpaRepository roomJpaRepository;
    private final RoomMapperEntity roomMapperEntity;

    @Override
    public void sync() {
        roomJpaRepository.saveAll(
                roomMapperEntity.toEntity(
                    mapApiCollageService.getListRooms()
                )
        );
    }
}
