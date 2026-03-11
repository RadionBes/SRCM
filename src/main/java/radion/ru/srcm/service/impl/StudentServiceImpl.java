package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import radion.ru.srcm.dto.request.StudentCreateRequest;
import radion.ru.srcm.dto.request.StudentUpdateRequest;
import radion.ru.srcm.dto.request.StudentsCreateRequest;
import radion.ru.srcm.dto.response.StudentResponse;
import radion.ru.srcm.entity.Student;
import radion.ru.srcm.exceptions.ItemExistsException;
import radion.ru.srcm.exceptions.NotFoundByIdException;
import radion.ru.srcm.mapper.entity.StudentMapperEntity;
import radion.ru.srcm.mapper.response.StudentMapperResponse;
import radion.ru.srcm.dao.StudentJpaRepository;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.StudentService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentJpaRepository repository;
    private final StudentMapperEntity studentMapperEntity;
    private final StudentMapperResponse studentMapperResponse;
    private final GroupService groupService;
    private final MessageSource messageSource;

    @Override
    public StudentResponse create(StudentCreateRequest studentsCreateRequest) throws ItemExistsException{
        var group = groupService.getGroupById(studentsCreateRequest.getIdGroup());
        var entity = studentMapperEntity.toEntity(studentsCreateRequest);
        if (!repository.existsStudentByGroupAndFullName(group, studentsCreateRequest.getFullName())) {
            entity.setGroup(group);
            return studentMapperResponse.toResponse(
                    repository.save(entity)
            );
        } else {
            throw new ItemExistsException(
                    messageSource.getMessage(
                            "warning.StudentAlreadyExists",
                            new Object[]{studentsCreateRequest.getFullName()},
                            null)
            );
        }
    }

    @Override
    public List<StudentResponse> createStudentsFromList(StudentsCreateRequest students) {
        var group = groupService.getGroupById(students.getGroupId());
        var entities = studentMapperEntity.toEntity(students.getStudents());
        entities.stream()
                .filter(el -> !repository.existsStudentByGroupAndFullName(group, el.getFullName()))
                .forEach(el -> el.setGroup(group));
        return studentMapperResponse.toResponse(
                repository.saveAll(entities)
        );
    }

    @Override
    public List<StudentResponse> getAll() {
        return studentMapperResponse.toResponse(
                repository.findAll()
        );
    }

    @Override
    public StudentResponse update(StudentUpdateRequest studentUpdateRequest) {
        Student student = repository.findById(studentUpdateRequest.getId()).orElseThrow();

        Optional.ofNullable(studentUpdateRequest.getCity()).ifPresent(student::setCity);
        Optional.ofNullable(studentUpdateRequest.getFullName()).ifPresent(student::setFullName);
        Optional.ofNullable(studentUpdateRequest.getDescription()).ifPresent(student::setDescription);
        Optional.ofNullable(studentUpdateRequest.getInterest()).ifPresent(student::setInterest);
        Optional.ofNullable(studentUpdateRequest.getGroupId()).ifPresent(el ->
                student.setGroup(
                        groupService.getGroupById(el)
                )
        );

        return studentMapperResponse.toResponse(
                repository.save(student)
        );
    }

    @Override
    public StudentResponse getById(Long id) throws NotFoundByIdException {
        return studentMapperResponse.toResponse(
                repository.findById(id).orElseThrow(() ->
                        new NotFoundByIdException(
                                messageSource.getMessage(
                                        "warning.StudentNotFound",
                                        new Object[]{id},
                                        null
                                )
                        )
                )
        );
    }
}
