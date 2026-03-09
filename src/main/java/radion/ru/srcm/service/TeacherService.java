package radion.ru.srcm.service;

import radion.ru.srcm.entity.Teacher;

import java.util.List;

public interface TeacherService {
    void sync();

    List<Teacher> getAll();
}
