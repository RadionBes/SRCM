package radion.ru.srcm.service;

import radion.ru.srcm.dto.StudentDto;
import radion.ru.srcm.dto.StudentsCreateRequest;
import radion.ru.srcm.entity.Student;

import java.util.List;

public interface StudentService {
     Student create(StudentDto studentDto, Long groupId);
     void createStudentsFromList(StudentsCreateRequest students);

     List<Student> getAll();
}
