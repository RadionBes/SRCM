package radion.ru.srcm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import radion.ru.srcm.exceptions.impl.ResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseException> itemExistsExceptionHandler(ItemExistsException itemExistsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseException(HttpStatus.BAD_REQUEST.value(), itemExistsException.getMessage())
        );
    }

    @ExceptionHandler(NotFoundByIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseException> notFoundByIdExceptionHandler(NotFoundByIdException notFoundByIdException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseException(HttpStatus.BAD_REQUEST.value(), notFoundByIdException.getMessage())
        );
    }

    @ExceptionHandler(NotFoundByKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseException> notFoundByKeyExceptionHandler(NotFoundByKeyException notFoundByKeyException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseException(HttpStatus.BAD_REQUEST.value(), notFoundByKeyException.getMessage())
        );
    }
}
