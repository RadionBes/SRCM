package radion.ru.srcm.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.response.StudentResponse;
import radion.ru.srcm.dto.request.StudentUpdateRequest;
import radion.ru.srcm.dto.request.StudentsCreateRequest;
import radion.ru.srcm.entity.Student;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapperResponse {
    StudentResponse toResponse(StudentUpdateRequest studentUpdateRequest);
    StudentResponse toResponse(StudentsCreateRequest studentsCreateRequest);
    StudentResponse toResponse(Student student);

    List<StudentResponse> toResponse(List<Student> students);
}
