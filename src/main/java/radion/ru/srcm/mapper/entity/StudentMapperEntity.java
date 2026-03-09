package radion.ru.srcm.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.request.StudentCreateRequest;
import radion.ru.srcm.dto.StudentDto;
import radion.ru.srcm.dto.request.StudentUpdateRequest;
import radion.ru.srcm.dto.request.StudentsCreateRequest;
import radion.ru.srcm.entity.Student;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapperEntity {
    @Mapping(target = "id", ignore = true)
    Student toEntity(StudentDto studentDto);
    Student toEntity(StudentsCreateRequest studentsCreateRequest);
    Student toEntity(StudentCreateRequest studentCreateRequest);
    List<Student> toEntity(List<StudentsCreateRequest.StudentRequestItem> studentsDto);
    Student toEntity(StudentUpdateRequest studentUpdateRequest);
}