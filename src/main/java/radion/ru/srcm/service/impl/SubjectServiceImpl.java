package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dto.response.SubjectResponse;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.entity.Subject;
import radion.ru.srcm.mapper.entity.SubjectMapperEntity;
import radion.ru.srcm.mapper.response.SubjectMapperResponse;
import radion.ru.srcm.repository.SubjectJpaRepository;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.SubjectService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectJpaRepository subjectJpaRepository;
    private final SubjectMapperEntity subjectMapperEntity;
    private final SubjectMapperResponse subjectMapperResponse;
    private final MapApiCollageService mapApiCollageService;
    private final GroupService groupService;

    @Override
    public List<SubjectResponse> getAllSubjectById(Long id) {
        return subjectMapperResponse.toResponse(
                subjectJpaRepository.findAllById(id)
        );
    }

    @Override
    public void syncAllGroup() {
        List<Group> groupsEntityList = groupService.getAll();
        Set<Subject> subjectList = new HashSet<>();
        groupsEntityList.forEach(el -> {
                    List<Subject> entities = subjectMapperEntity.toEntity(
                            mapApiCollageService
                                    .getListSubjectForGroup(el.getKey())
                    );
                    entities.forEach(en -> en.setGroup(el));
                    el.getSubjects().addAll(entities);
                    subjectList.addAll(entities);
                }
        );
        subjectJpaRepository.saveAll(subjectList);
        groupService.saveAll(groupsEntityList);
    }

}
