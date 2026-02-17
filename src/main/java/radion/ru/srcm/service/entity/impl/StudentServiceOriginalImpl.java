package radion.ru.srcm.service.entity.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.dao.StudentJpaRepository;
import radion.ru.srcm.entity.Student;
import radion.ru.srcm.service.entity.StudentServiceOriginal;

@Service
@RequiredArgsConstructor
public class StudentServiceOriginalImpl implements StudentServiceOriginal {
    private StudentJpaRepository studentJpaRepository;

    @Override
    public Student getById(Long id) {
        return studentJpaRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
