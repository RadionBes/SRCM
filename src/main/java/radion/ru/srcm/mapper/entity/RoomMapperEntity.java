package radion.ru.srcm.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.api.RoomApiDto;
import radion.ru.srcm.entity.Room;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapperEntity {
    @Mapping(target = "utf", source = "UTF")
    Room toEntity(RoomApiDto roomApiDto);
    @Mapping(target = "utf", source = "UTF")
    List<Room> toEntity(List<RoomApiDto> roomApiDtos);
}
