package radion.ru.srcm.service.entity.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import radion.ru.srcm.dao.StudentJpaRepository;
import radion.ru.srcm.entity.Student;
import radion.ru.srcm.exceptions.NotFoundByIdException;
import radion.ru.srcm.logging.Loggable;
import radion.ru.srcm.service.entity.StudentServiceOriginal;

@Service
@RequiredArgsConstructor
public class StudentServiceOriginalImpl implements StudentServiceOriginal {
    private final StudentJpaRepository studentJpaRepository;
    private final MessageSource messageSource;

    @Override
    @Loggable(value = "Получение студента", logResult = false)
    public Student getById(Long id) {
        return studentJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundByIdException(
                        messageSource.getMessage(
                                "warning.NotFoundGroupById",
                                new Object[]{id},
                                null
                        )
                )
        );
    }
}
