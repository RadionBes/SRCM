package radion.ru.srcm.exceptions.impl;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ResponseExceptionModel {
    private int code;
    private Map<String, String> errors;
}
