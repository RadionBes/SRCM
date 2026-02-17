package radion.ru.srcm.service;

import radion.ru.srcm.dto.request.StudentCreateRequest;
import radion.ru.srcm.dto.request.StudentUpdateRequest;
import radion.ru.srcm.dto.request.StudentsCreateRequest;
import radion.ru.srcm.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse create(StudentCreateRequest studentsCreateRequest, Long groupId);
    void createStudentsFromList(StudentsCreateRequest students);
    List<StudentResponse> getAll();
    StudentResponse update(StudentUpdateRequest studentUpdateRequest);
    StudentResponse getById(Long id);
}
