package radion.ru.srcm.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupResponseNotStudents {
    private Long id;

    private String key;
    private String name;
    private String year;
    private String spec;
    private String hoz;

    private String curator;
}
