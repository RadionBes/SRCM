package radion.ru.srcm.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.response.SubjectResponse;
import radion.ru.srcm.entity.Subject;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubjectMapperResponse {
    SubjectResponse toResponse(Subject subject);
    List<SubjectResponse> toResponse(List<Subject> subjects);
}
