package radion.ru.srcm.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.dto.StudentDto;

import java.util.List;

@Data
@Builder
public class StudentsCreateRequest {
    @NotNull
    private Long groupId;
    @Valid
    private List<StudentCreateRequest> students;
}
