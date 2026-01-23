package radion.ru.srcm.service;


import radion.ru.srcm.dto.GroupResponse;
import radion.ru.srcm.entity.Group;

import java.util.List;

public interface GroupService {
    void syncGroup();
    List<GroupResponse> getGroupList();
    Group getGroupById(Long groupId);
}
