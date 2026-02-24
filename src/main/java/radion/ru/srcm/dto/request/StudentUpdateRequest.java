package radion.ru.srcm.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.util.Interest;

@Data
@Builder
public class StudentUpdateRequest {
    @NotNull
    private Long id;
    @Size(min = 10)
    private String fullName;
    private String city;
    @NotNull
    private Interest interest;
    private String description;

    @NotNull
    private Long groupId;
}
