package radion.ru.srcm.service;


import radion.ru.srcm.dto.ResponseGroupList;

import java.util.List;

public interface GroupService {
    void syncGroup();
    List<ResponseGroupList> getGroupList();
}
