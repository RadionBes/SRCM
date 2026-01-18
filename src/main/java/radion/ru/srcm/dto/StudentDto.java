package radion.ru.srcm.dto;

import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.util.Interest;

@Data
@Builder
public class StudentDto {
    private String fullName;
    private String city;
    private Interest interest;
}
