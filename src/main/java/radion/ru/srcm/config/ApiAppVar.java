package radion.ru.srcm.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Класс для хранения адресов и эндпоинтов сервера колледжа.
 * <p>
 * Предназначен для автоматической привязки свойств из конфигурационных файлов Spring Boot
 * (например, {@code application.yml} или {@code application.properties}) с префиксом {@code app.collage}.
 * </p>
 *
 * <p><b>Пример конфигурации в {@code application.yml}:</b></p>
 * <pre>
 * app:
 *   collage:
 *     base-url: http://college-api.example.com/api
 *     endpoints:
 *       pairs: /schedule/pairs
 *       rooms: /dictionaries/rooms
 *       groups: /dictionaries/groups
 *       weeks: /schedule/weeks
 *       teachers: /dictionaries/teachers
 *       subjects: /dictionaries/subjects
 *       timetable: /timetable/teacher
 * </pre>
 *
 * <p>После привязки объект можно внедрить как Spring-бин и использовать для построения URL-адресов:</p>
 * <pre>
 * String url = apiAppVar.getBaseUrl() + apiAppVar.getEndpoints().getGroups();
 * </pre>
 *
 * @author RadionBes
 */
@Component
@ConfigurationProperties(prefix = "app.collage")
@Getter
@Setter
public class ApiAppVar {
    /**
     * Базовый URL-адрес сервера колледжа (схема + домен + порт + контекстный путь).
     * <p>
     * Примеры: {@code http://college.local/api} или {@code https://college.example.com/v1}
     * </p>
     * <p>Не должен заканчиваться слэшем (конкатенация с эндпоинтами выполняется через {@code +}).</p>
     */
    private String baseUrl;
    /**
     * Внутренний класс, группирующий эндпоинты (конкретные пути) для различных операций.
     * <p>
     * Все поля являются относительными путями и должны конкатенироваться с {@link ApiAppVar#baseUrl}.
     * Например: {@code baseUrl + "/groups"}.
     * </p>
     */
    private Endpoints endpoints = new Endpoints();

    @Setter
    @Getter
    public static class Endpoints{
        /**
         * Эндпоинт для получения списка пар (занятий).
         * Пример значения: {@code /schedule/pairs}
         */
        private String pairs;
        /**
         * Эндпоинт для получения списка аудиторий (кабинетов).
         * Пример значения: {@code /dictionaries/rooms}
         */
        private String rooms;

        /**
         * Эндпоинт для получения списка групп.
         * Пример значения: {@code /dictionaries/groups}
         */
        private String groups;

        /**
         * Эндпоинт для получения списка учебных недель.
         * Пример значения: {@code /schedule/weeks}
         */
        private String weeks;

        /**
         * Эндпоинт для получения списка преподавателей.
         * Пример значения: {@code /dictionaries/teachers}
         */
        private String teachers;

        /**
         * Эндпоинт для получения списка предметов.
         * Пример значения: {@code /dictionaries/subjects}
         */
        private String subjects;

        /**
         * Эндпоинт для получения расписания преподавателя.
         * Пример значения: {@code /timetable/teacher}
         */
        private String timetable;
    }
}
