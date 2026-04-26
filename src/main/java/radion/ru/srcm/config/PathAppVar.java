package radion.ru.srcm.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Компонент для привязки внешних конфигурационных свойств, связанных с файловыми путями.
 * <p>
 * Свойства читаются из файлов конфигурации Spring Boot (например, {@code application.yml}
 * или {@code application.properties}) с префиксом {@code app.file-path}.
 * </p>
 *
 * <p><b>Пример в {@code application.yml}:</b></p>
 * <pre>
 * app:
 *   file-path:
 *     groups: /data/college/groups.json
 *     students: /data/college/students.json
 * </pre>
 *
 * <p><b>Пример в {@code application.properties}:</b></p>
 * <pre>
 * app.file-path.groups=/data/college/groups.json
 * app.file-path.students=/data/college/students.json
 * </pre>
 *
 * <p>После инициализации компонент можно внедрить в любое место приложения:</p>
 * <pre>
 * &#64;Autowired
 * private PathAppVar pathAppVar;
 *
 * public void loadGroups() {
 *     String path = pathAppVar.getGroups();
 *     // чтение файла по указанному пути
 * }
 * </pre>
 *
 * @author radion.ru
 */
@Component
@ConfigurationProperties(prefix = "app.file-path")
@Getter
@Setter
public class PathAppVar {
    /**
     * Путь к файлу со списком групп.
     * <p>
     * Может быть абсолютным или относительным (относительно рабочей директории приложения).
     * Пример: {@code /data/groups.json}
     * </p>
     */
    private String groups;
    /**
     * Путь к файлу со списком студентов.
     * <p>
     * Может быть абсолютным или относительным (относительно рабочей директории приложения).
     * Пример: {@code /data/students.json}
     * </p>
     */
    private String students;
}
