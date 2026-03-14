package radion.ru.srcm.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {
    String value() default ""; // Дополнительное описание
    boolean logParams() default true; // Логировать параметры?
    boolean logResult() default true; // Логировать результат?
}
