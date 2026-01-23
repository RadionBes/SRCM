package radion.ru.srcm.dto.request;

import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.util.Interest;

@Data
@Builder
public class StudentUpdateRequest {
    private Long id;

    private String fullName;
    private String city;
    private Interest interest;
}
