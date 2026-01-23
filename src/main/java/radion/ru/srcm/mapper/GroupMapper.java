package radion.ru.srcm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.GroupResponse;
import radion.ru.srcm.dto.api.GroupApiDto;
import radion.ru.srcm.entity.Group;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {
    @Mapping(target = "id", ignore = true)
    Group toEntity(GroupApiDto groupApiDto);
    GroupResponse toDto(Group group);
    List<GroupResponse> toDto(List<Group> groups);
    radion.ru.srcm.dto.response.GroupResponse toDtoResponse(Group group);
}
