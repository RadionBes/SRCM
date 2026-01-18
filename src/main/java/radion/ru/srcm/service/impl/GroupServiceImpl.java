package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dto.ResponseGroupList;
import radion.ru.srcm.mapper.GroupMapper;
import radion.ru.srcm.repository.GroupJpaRepository;
import radion.ru.srcm.service.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupJpaRepository repository;
    private final MapApiCollageService apiCollageService;
    private final GroupMapper groupMapper;

    @Override
    public void syncGroup() {
        var groups = apiCollageService.getListGroup();
        groups.stream()
                .filter(el -> !repository.existsGroupByKey(el.getKey()))
                .forEach(el ->
                        repository.save(
                                groupMapper.toEntity(el)
                        )
                );
    }

    @Override
    public List<ResponseGroupList> getGroupList() {
        return List.of();
    }
}
