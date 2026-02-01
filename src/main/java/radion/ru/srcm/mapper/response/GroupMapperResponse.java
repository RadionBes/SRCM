package radion.ru.srcm.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.entity.Group;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                SubjectMapperResponse.class
        }
)
public interface GroupMapperResponse {

    @Mapping(target = "subjects", source = "subjects")
    GroupResponse toResponse(Group group);

    List<GroupResponse> toResponse(List<Group> groups);
}
