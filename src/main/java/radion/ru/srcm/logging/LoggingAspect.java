package radion.ru.srcm.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    // Единый pointcut для всех методов с аннотацией @Loggable (исключая уже перехваченные)
    @Pointcut("@annotation(radion.ru.srcm.logging.Loggable)")
    public void loggableMethodsOtherServices() {}

    // Для методов с аннотацией в других сервисах
    @Around("loggableMethodsOtherServices()")
    public Object logAnnotatedMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Loggable loggable = signature.getMethod().getAnnotation(Loggable.class);
        return logMethodWithAnnotation(joinPoint, loggable);
    }

    // Логирование с учетом аннотации
    private Object logMethodWithAnnotation(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        StringBuilder logMessage = new StringBuilder();
        if (!loggable.value().isEmpty()) {
            logMessage.append("[").append(loggable.value()).append("] ");
        }
        logMessage.append(className).append(".").append(methodName);

        long startTime = System.currentTimeMillis();

        try {
            if (loggable.logParams()) {
                String params = getParametersAsString(joinPoint.getArgs());
                log.info("▶️ {} - Вход с параметрами: [{}]", logMessage, params);
            } else {
                log.info("▶️ {} - Выполнение", logMessage);
            }

            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            if (loggable.logResult() && result != null) {
                String resultStr = truncateString(safeSerialize(result));
                log.info("✅ {} - Завершено за {} мс. Результат: {}", logMessage, duration, resultStr);
            } else {
                log.info("✅ {} - Завершено за {} мс", logMessage, duration);
            }

            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            log.error("❌ {} - Ошибка через {} мс: {}", logMessage, duration, e.getMessage());
            throw e;
        }
    }

    // Вспомогательные методы (без изменений)
    private String getParametersAsString(Object[] args) {
        if (args == null || args.length == 0) {
            return "";
        }
        return Arrays.stream(args)
                .map(this::safeSerialize)
                .collect(Collectors.joining(", "));
    }

    private String safeSerialize(Object obj) {
        if (obj == null) return "null";
        if (obj instanceof List && ((List<?>) obj).size() > 10) {
            return "List(size=" + ((List<?>) obj).size() + ")";
        }
        try {
            return truncateString(mapper.writeValueAsString(obj));
        } catch (Exception e) {
            return obj.toString();
        }
    }

    private String truncateString(String str) {
        if (str != null && str.length() > 200) {
            return str.substring(0, 197) + "...";
        }
        return str;
    }
}