package radion.ru.srcm.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentsResponse {
    List<StudentResponse> students;
}
