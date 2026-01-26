package radion.ru.srcm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.GroupListResponse;
import radion.ru.srcm.dto.api.GroupApiDto;
import radion.ru.srcm.entity.Group;

import java.util.List;

@Deprecated
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {
    @Mapping(target = "id", ignore = true)
    Group toEntity(GroupApiDto groupApiDto);
    GroupListResponse toDto(Group group);
    List<GroupListResponse> toDto(List<Group> groups);
}
