package radion.ru.srcm.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.api.GroupApiDto;
import radion.ru.srcm.entity.Group;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapperEntity {
    @Mapping(target = "id", ignore = true)
    Group toEntity(GroupApiDto groupApiDto);
}
