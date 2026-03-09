package radion.ru.srcm.exceptions;

public class NotFoundByKeyException extends RuntimeException{
    public NotFoundByKeyException(String message) {
        super(message);
    }
}
