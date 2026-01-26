package radion.ru.srcm.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SubjectResponse {
    private Long id;

    private String key;
    private String subject;
    private String hours;
    private String first;
}
