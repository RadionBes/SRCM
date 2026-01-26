package radion.ru.srcm.dto.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectApiDto {
    private String Key;
    private String Subject;
    private String Hours;
    private String First;
}
