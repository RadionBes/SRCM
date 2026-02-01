package radion.ru.srcm.service;


import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.entity.Group;

import java.util.List;

public interface GroupService {
    void syncGroup();
    List<GroupResponse> getGroupList();
    List<Group> getAll();
    Group getGroupById(Long groupId);
    Group getGroupByKey(String key);

    void save(Group group);
    void saveAll(List<Group> groups);
}
