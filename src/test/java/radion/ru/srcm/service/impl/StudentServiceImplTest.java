package radion.ru.srcm.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import radion.ru.srcm.dao.StudentJpaRepository;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.entity.Student;
import radion.ru.srcm.mapper.entity.StudentMapperEntity;
import radion.ru.srcm.mapper.response.StudentMapperResponse;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.util.Interest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock private StudentJpaRepository repository;
    @Mock private StudentMapperEntity studentMapperEntity;
    @Mock private StudentMapperResponse studentMapperResponse;
    @Mock private GroupService groupService;
    @InjectMocks private StudentServiceImpl studentService;

    private Group group = Group.builder()
            .id(1L)
            .key("101")
            .name("LOL")
            .year("2026")
            .spec("1")
            .hoz("1")
            .curator("1")
            .description("lvkvmfnvnm")
            .subjects(null)
            .files(null)
            .studentList(null)
            .timetables(null)
            .build();
    private Student student = Student.builder()
            .id(1L)
            .fullName("Stu1")
            .city("Pyatigorsk")
            .interest(Interest.NOT_INTERESTING)
            .description("knvfjnv")
            .group(group)
            .files(null)
            .build();

    @BeforeEach
    void setUp() {
    }
}