package radion.ru.srcm.config;

import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

/**
 * Конфигурационный класс приложения, определяющий бины для HTTP-клиента и CORS-настроек.
 * <p>
 * Используется в Spring Boot приложении для централизованного объявления компонентов,
 * необходимых на уровне всего приложения.
 * </p>
 *
 * @author RadionBes
 */
@Configuration
public class AppConfig {
    /**
     * Создаёт и настраивает бин {@link OkHttpClient} для выполнения HTTP-запросов.
     * <p>
     * Клиент сконфигурирован с таймаутом вызова (call timeout) 30 секунд.
     * Это максимальное время ожидания завершения всего вызова (установка соединения,
     * отправка запроса, получение ответа).
     * </p>
     *
     * @return настроенный экземпляр OkHttpClient (обязательно не null)
     */
    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(30))
                .build();
    }

    /**
     * Создаёт бин {@link WebMvcConfigurer} для настройки глобальной политики CORS.
     * <p>
     * Разрешаются все источники (allowedOrigins = "*"), все методы и все заголовки.
     * Это упрощённая конфигурация, подходящая для разработки или открытого API.
     * Для production рекомендуется ограничить допустимые источники.
     * </p>
     * <p>Настройка применяется ко всем эндпоинтам (mapping = "/**").</p>
     *
     * @return объект WebMvcConfigurer с переопределённым методом addCorsMappings
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
