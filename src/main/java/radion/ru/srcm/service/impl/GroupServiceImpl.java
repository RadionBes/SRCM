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
import radion.ru.srcm.logging.Loggable;
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
    @Loggable(value = "Синхронизация групп", logParams = false, logResult = false)
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
    @Loggable(value = "Получение списка групп", logParams = false)
    public List<GroupResponse> getGroupList() {
        return groupMapperResponse.toResponse(
                repository.findAll()
        );
    }

    @Override
    @Loggable(value = "Получение всех групп", logParams = false)
    public List<Group> getAll() {
        return repository.findAll();
    }
    @Override
    @Loggable(value = "Поиск группы по ID", logResult = false)
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
    @Loggable(value = "Поиск группы по KEY", logResult = false)
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
    @Loggable(value = "Сохранение группы", logResult = false)
    public void save(Group group) {
        repository.save(group);
    }
    @Override
    @Transactional
    @Loggable(value = "Сохранение списка групп", logResult = false)
    public void saveAll(List<Group> groups) {
        repository.saveAll(groups);
    }
}