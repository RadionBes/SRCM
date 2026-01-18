package radion.ru.srcm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import radion.ru.srcm.dto.GroupApiDto;
import radion.ru.srcm.dto.GroupResponse;
import radion.ru.srcm.dto.ResponseGroupForList;
import radion.ru.srcm.entity.Group;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {
    Group toEntity(GroupApiDto groupApiDto);
    ResponseGroupForList toDto(Group group);
    List<ResponseGroupForList> toDto(List<Group> groups);
    GroupResponse toDtoResponse(Group group);
}
