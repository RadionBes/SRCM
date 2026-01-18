package radion.ru.srcm.dto;

import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.util.Interest;

import java.util.List;

@Data
@Builder
public class StudentsCreateRequest {
    private Long groupId;
    private List<StudentDto> students;
}
