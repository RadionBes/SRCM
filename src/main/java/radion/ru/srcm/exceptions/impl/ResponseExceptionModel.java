package radion.ru.srcm.exceptions.impl;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Builder
public class ResponseExceptionModel {
    private int code;
    private Map<String, String> errors;

    public ResponseExceptionModel(int code) {
        this.code = code;
    }

}
