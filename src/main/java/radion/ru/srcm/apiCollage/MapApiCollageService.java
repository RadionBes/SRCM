package radion.ru.srcm.apiCollage;

import radion.ru.srcm.dto.api.*;
import radion.ru.srcm.logging.Loggable;

import java.util.List;

public interface MapApiCollageService {
    List<GroupApiDto> getListGroup();
    List<SubjectApiDto> getListSubjectForGroup(String key);
    List<String> getListWeeks();
    List<PairApiDto> getListPairs();
    List<RoomApiDto> getListRooms();
    List<TeacherApiDto> getListTeachers();
    List<TimetableTeacherApiDto> getListTimetableTeacher();

}
