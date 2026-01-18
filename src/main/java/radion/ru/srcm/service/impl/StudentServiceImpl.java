package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.dto.StudentDto;
import radion.ru.srcm.dto.StudentsCreateRequest;
import radion.ru.srcm.entity.Student;
import radion.ru.srcm.mapper.GroupMapper;
import radion.ru.srcm.mapper.StudentMapper;
import radion.ru.srcm.repository.StudentJpaRepository;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.StudentService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentJpaRepository repository;
    private final StudentMapper mapper;
    private final GroupService groupService;
    private final GroupMapper groupMapper;
    @Override
    public Student create(StudentDto studentDto, Long groupId) {
        var group = groupService.getGroupById(groupId);
        var entity = mapper.toEntity(studentDto);
        entity.setGroup(group);
        return repository.save(entity);
    }

    @Override
    public void createStudentsFromList(StudentsCreateRequest students) {
        var group = groupService.getGroupById(students.getGroupId());
        var entities = mapper.toEntity(students.getStudents());
        entities.forEach(el -> el.setGroup(group));
        repository.saveAll(entities);
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }
}
