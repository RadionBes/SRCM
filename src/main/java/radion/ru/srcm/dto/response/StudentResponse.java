package radion.ru.srcm.dto.response;

import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.util.Interest;

@Data
@Builder
public class StudentResponse {
    private Long id;

    private String fullName;
    private String city;
    private Interest interest;
    private String description;

    private GroupResponseNotStudents group;
}
