package radion.ru.srcm.dto.request;

import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.dto.StudentDto;

import java.util.List;

@Data
@Builder
public class StudentsCreateRequest {
    private Long groupId;
    private List<StudentCreateRequest> students;
}
