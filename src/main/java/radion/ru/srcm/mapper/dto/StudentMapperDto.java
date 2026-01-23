package radion.ru.srcm.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.StudentDto;
import radion.ru.srcm.entity.Student;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapperDto {
    StudentDto toDto(Student student);
}
