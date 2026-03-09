package radion.ru.srcm.exceptions.impl;


public record ResponseException(
        int status,
        String message
){}
