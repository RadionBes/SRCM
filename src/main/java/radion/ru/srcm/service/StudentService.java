package radion.ru.srcm.service;

import radion.ru.srcm.dto.request.StudentCreateRequest;
import radion.ru.srcm.dto.request.StudentUpdateRequest;
import radion.ru.srcm.dto.request.StudentsCreateRequest;
import radion.ru.srcm.dto.response.StudentResponse;
import radion.ru.srcm.dto.response.StudentsResponse;

public interface StudentService {
    StudentResponse create(StudentCreateRequest studentsCreateRequest, Long groupId);
    void createStudentsFromList(StudentsCreateRequest students);
    StudentsResponse getAll();
    StudentResponse update(StudentUpdateRequest studentUpdateRequest);
    StudentResponse getById(Long id);
}
