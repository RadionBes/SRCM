package radion.ru.srcm.dto;

import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.util.Interest;

@Data
@Builder
@Deprecated
public class StudentDto {
    private Long id;

    private String fullName;
    private String city;
    private Interest interest;
    private String description;
}
