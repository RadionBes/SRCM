package radion.ru.srcm.dto;

import lombok.Builder;
import lombok.Data;

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

    private List<StudentDto> studentList;
}
