package radion.ru.srcm.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResponse {
    private Long id;

    private String name;
    private String fileType;
    private String size;

    private GroupResponse group;
    private StudentResponse student;
}
