package radion.ru.srcm.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.file-path")
@Getter
@Setter
public class PathAppVar {
    private String groups;
    private String students;
}
