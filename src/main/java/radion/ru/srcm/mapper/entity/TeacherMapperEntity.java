package radion.ru.srcm.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.api.TeacherApiDto;
import radion.ru.srcm.entity.Teacher;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapperEntity {
    Teacher toEntity(TeacherApiDto teacherApiDto);
    List<Teacher> toEntity(List<TeacherApiDto> teacherApiDtos);
}
