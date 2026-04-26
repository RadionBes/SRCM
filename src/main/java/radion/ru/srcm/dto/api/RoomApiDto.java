package radion.ru.srcm.dto.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

/**
 * DTO для передачи данных об аудитории (кабинете), полученных от API колледжа.
 */
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class RoomApiDto {
    private String key;
    private String UTF;
}
