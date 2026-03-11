package radion.ru.srcm.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.exceptions.NotFoundByIdException;
import radion.ru.srcm.exceptions.NotFoundByKeyException;
import radion.ru.srcm.mapper.entity.GroupMapperEntity;
import radion.ru.srcm.mapper.response.GroupMapperResponse;
import radion.ru.srcm.dao.GroupJpaRepository;
import radion.ru.srcm.service.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupJpaRepository repository;
    private final MapApiCollageService apiCollageService;
    private final GroupMapperEntity mapper;
    private final GroupMapperResponse groupMapperResponse;
    private final MessageSource messageSource;

    @Override
    public void sync() {
        var groups = apiCollageService.getListGroup();
        groups.stream()
                .filter(el -> !repository.existsGroupByKey(el.getKey()))
                .forEach(el ->
                        repository.save(
                                mapper.toEntity(el)
                        )
                );
    }
    @Override
    public List<GroupResponse> getGroupList() {
        return groupMapperResponse.toResponse(
                repository.findAll()
        );
    }
    @Override
    public List<Group> getAll() {
        return repository.findAll();
    }
    @Override
    public Group getGroupById(Long groupId) {
        return repository.findById(groupId).orElseThrow(() ->
                new NotFoundByIdException(
                        messageSource.getMessage(
                                "warning.NotFoundGroupById",
                                new Object[]{groupId},
                                null
                        )
                )
        );
    }
    @Override
    public Group getGroupByKey(String key) {
        return repository.findGroupByKey(key).orElseThrow(() ->
                new NotFoundByKeyException(
                        messageSource.getMessage(
                                "warning.NotFoundGroupByKey",
                                new Object[]{key},
                                null
                        )
                ));
    }
    @Override
    @Transactional
    public void save(Group group) {
        repository.save(group);
    }
    @Override
    public void saveAll(List<Group> groups) {
        repository.saveAll(groups);
    }
}