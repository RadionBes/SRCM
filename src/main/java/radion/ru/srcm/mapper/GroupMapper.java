package radion.ru.srcm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import radion.ru.srcm.dto.GroupApiDto;
import radion.ru.srcm.entity.Group;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {
    Group toEntity(GroupApiDto groupApiDto);
}
