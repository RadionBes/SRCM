package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dao.TeacherJpaRepository;
import radion.ru.srcm.mapper.entity.TeacherMapperEntity;
import radion.ru.srcm.service.TeacherService;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final MapApiCollageService mapApiCollageService;
    private final TeacherMapperEntity teacherMapperEntity;
    private final TeacherJpaRepository teacherJpaRepository;
    @Override
    public void sync() {
        teacherJpaRepository.saveAll(
                teacherMapperEntity.toEntity(
                        mapApiCollageService.getListTeachers()
                )
        );
    }
}
