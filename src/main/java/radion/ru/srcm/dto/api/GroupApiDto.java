package radion.ru.srcm.dto.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

/**
 * DTO для передачи информации об учебной группе, полученной от внешнего API колледжа.
 * <p>
 * Имена свойств преобразуются в стиль UpperCamelCase (например, {@code Key}, {@code Name})
 * в соответствии с настройкой {@link JsonNaming}.
 * </p>
 *
 * @see PropertyNamingStrategies.UpperCamelCaseStrategy
 */

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class GroupApiDto {
    /**
     * Уникальный строковый идентификатор группы (например, "ИС-31").
     * Обычно соответствует коду в учебной системе.
     */
    private String key;

    /**
     * Полное название группы (например, "Информационные системы 3 курс").
     */
    private String name;

    /**
     * Год набора или номер курса (например, "2023", "3").
     */
    private String year;

    /**
     * Специальность / направление подготовки (например, "09.02.04 Информационные системы").
     */
    private String spec;

    /**
     * Код или название хозяйственной группы (может использоваться для внутреннего учёта).
     * Значение может быть {@code null} или пустым, если не применимо.
     */
    private String hoz;
}
