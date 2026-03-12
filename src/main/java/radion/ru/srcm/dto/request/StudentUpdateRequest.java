package radion.ru.srcm.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.util.Interest;

@Data
@Builder
public class StudentUpdateRequest {
    @NotNull(message = "{messages.not.null}")
    private Long id;
    @Size(min = 10, message = "{messages.min.size}")
    private String fullName;
    @Size(max = 1000, message = "{messages.max.size}")
    private String city;
    @NotNull(message = "{messages.not.null}")
    private Interest interest;
    @Size(max = 1500, message = "{messages.max.size}")
    private String description;

    @NotNull(message = "{messages.not.null}")
    private Long groupId;
}
