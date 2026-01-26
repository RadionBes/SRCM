package radion.ru.srcm.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.api.SubjectApiDto;
import radion.ru.srcm.entity.Subject;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubjectMapperEntity {
    Subject toEntity(SubjectApiDto apiDto);
    List<Subject> toEntity(List<SubjectApiDto> apiDtos);
}
