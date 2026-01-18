package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dto.ResponseGroupForList;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.mapper.GroupMapper;
import radion.ru.srcm.repository.GroupJpaRepository;
import radion.ru.srcm.service.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupJpaRepository repository;
    private final MapApiCollageService apiCollageService;
    private final GroupMapper mapper;

    @Override
    public void syncGroup() {
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
    public List<ResponseGroupForList> getGroupList() {
        return mapper.toDto(
                repository.findAll()
        );
    }

    @Override
    public Group getGroupInfo(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Group getGroupById(Long groupId) {
        return repository.findById(groupId).orElse(null);
    }


}
