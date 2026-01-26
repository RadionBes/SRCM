package radion.ru.srcm.dto.response;

import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.dto.StudentDto;

import java.util.List;

@Data
@Builder
public class GroupResponse {
    private Long id;

    private String key;
    private String name;
    private String year;
    private String spec;
    private String hoz;

    private String curator;
    private String description;

    private List<SubjectResponse> subjects;
    private List<StudentDto> studentList;
}
