package radion.ru.srcm.service;


import radion.ru.srcm.dto.ResponseGroupForList;
import radion.ru.srcm.entity.Group;

import java.util.List;

public interface GroupService {
    void syncGroup();
    List<ResponseGroupForList> getGroupList();
    Group getGroupInfo(Long id);
    Group getGroupById(Long groupId);
}
