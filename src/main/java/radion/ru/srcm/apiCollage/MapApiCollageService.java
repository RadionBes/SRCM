package radion.ru.srcm.apiCollage;

import radion.ru.srcm.dto.api.GroupApiDto;
import radion.ru.srcm.dto.api.SubjectApiDto;

import java.util.List;

public interface MapApiCollageService {
    List<GroupApiDto> getListGroup();
    List<SubjectApiDto> getListSubjectForGroup(String key);
}
