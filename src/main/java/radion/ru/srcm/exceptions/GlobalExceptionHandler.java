package radion.ru.srcm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import radion.ru.srcm.exceptions.impl.ResponseException;
import radion.ru.srcm.exceptions.impl.ResponseExceptionModel;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseExceptionModel> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(el ->
                errors.put(el.getField(), el.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(
                ResponseExceptionModel.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .errors(errors)
                        .build()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseException> runtime(RuntimeException runtimeException){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR.value(), runtimeException.getMessage())
        );
    }

    @ExceptionHandler(ItemExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseException> itemExistsExceptionHandler(ItemExistsException itemExistsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseException(HttpStatus.BAD_REQUEST.value(), itemExistsException.getMessage())
        );
    }

    @ExceptionHandler(NotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseException> notFoundByIdExceptionHandler(NotFoundByIdException notFoundByIdException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseException(HttpStatus.NOT_FOUND.value(), notFoundByIdException.getMessage())
        );
    }

    @ExceptionHandler(NotFoundByKeyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseException> notFoundByKeyExceptionHandler(NotFoundByKeyException notFoundByKeyException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseException(HttpStatus.NOT_FOUND.value(), notFoundByKeyException.getMessage())
        );
    }
}
