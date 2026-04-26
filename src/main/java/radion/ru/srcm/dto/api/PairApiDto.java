package radion.ru.srcm.dto.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

/**
 * DTO для передачи данных об учебной паре (занятии), полученных от API колледжа.
 * <p>
 * Содержит информацию о временном слоте (позиция, время начала и окончания).
 * </p>
 */
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class PairApiDto {
    /**
     * Уникальный идентификатор пары (например, "1", "2", "ПЗ").
     * Может соответствовать номеру пары в расписании.
     */
    private String key;

    /**
     * Название или номер позиции пары (например, "1 пара", "2 пара").
     * Может совпадать с {@code key} или содержать текстовое описание.
     */
    private String position;

    /**
     * Время начала пары в строковом формате (например, "08:30", "9:00").
     * Формат зависит от API, может включать часовой пояс.
     */
    private String begin;

    /**
     * Время окончания пары в строковом формате (например, "10:05", "10:30").
     */
    private String end;
}
