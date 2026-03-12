package radion.ru.srcm.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.util.Interest;

@Data
@Builder
public class StudentCreateRequest {
    @NotBlank(message = "{messages.not.blank}")
    @Size(min = 10, message = "{messages.min.size}")
    private String fullName;
    @NotBlank(message = "{messages.not.blank}")
    private String city;
    @NotNull(message = "{messages.not.null}")
    private Interest interest;
    @Size(max = 1500, message = "{messages.not.blank}")
    private String description;
    @NotNull(message = "{messages.not.blank}")
    private Long idGroup;
}
