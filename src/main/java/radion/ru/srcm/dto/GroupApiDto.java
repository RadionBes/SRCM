package radion.ru.srcm.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class GroupApiDto {
    private String key;
    private String name;
    private String year;
    private String spec;
    private String hoz;
}
