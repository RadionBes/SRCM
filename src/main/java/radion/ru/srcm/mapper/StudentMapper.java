package radion.ru.srcm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import radion.ru.srcm.dto.StudentDto;
import radion.ru.srcm.entity.Student;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    Student toEntity(StudentDto studentDto);
    List<Student> toEntity(List<StudentDto> studentsDto);
}
