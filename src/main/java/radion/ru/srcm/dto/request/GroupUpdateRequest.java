package radion.ru.srcm.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupUpdateRequest {

    private String name;
    private String year;
    private String spec;
    private String hoz;

    private String curator;

    private String description;

}
