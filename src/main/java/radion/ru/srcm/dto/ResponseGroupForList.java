package radion.ru.srcm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGroupForList {
    private Long id;
    private String name;
}
