package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.dto.request.StudentCreateRequest;
import radion.ru.srcm.dto.request.StudentUpdateRequest;
import radion.ru.srcm.dto.request.StudentsCreateRequest;
import radion.ru.srcm.dto.response.StudentResponse;
import radion.ru.srcm.dto.response.StudentsResponse;
import radion.ru.srcm.mapper.entity.StudentMapperEntity;
import radion.ru.srcm.mapper.response.StudentMapperResponse;
import radion.ru.srcm.repository.StudentJpaRepository;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.StudentService;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentJpaRepository repository;
    private final StudentMapperEntity studentMapperEntity;
    private final StudentMapperResponse studentMapperResponse;

    private final GroupService groupService;

    @Override
    public StudentResponse create(StudentCreateRequest studentsCreateRequest, Long groupId) {
        var group = groupService.getGroupById(groupId);
        var entity = studentMapperEntity.toEntity(studentsCreateRequest);
        if (!repository.existsStudentByGroupAndFullName(group, studentsCreateRequest.getFullName())) {
            entity.setGroup(group);
            return studentMapperResponse.toResponse(
                    repository.save(entity)
            );
        } else {
            //TODO: Дописать предупреждение
            return null;
        }
    }

    @Override
    public void createStudentsFromList(StudentsCreateRequest students) {
        var group = groupService.getGroupById(students.getGroupId());
        var entities = studentMapperEntity.toEntity(students.getStudents());
        entities.stream()
                .filter(el -> !repository.existsStudentByGroupAndFullName(group, el.getFullName()))
                .forEach(el -> el.setGroup(group));
        repository.saveAll(entities);
    }

    @Override
    public StudentsResponse getAll() {
        return StudentsResponse.builder()
                .students(
                        studentMapperResponse.toResponse(
                                repository.findAll()
                        )
                )
                .build();
    }

    @Override
    public StudentResponse update(StudentUpdateRequest studentUpdateRequest) {
        return studentMapperResponse.toResponse(
                repository.save(
                        studentMapperEntity.toEntity(studentUpdateRequest)
                )
        );
    }
}
