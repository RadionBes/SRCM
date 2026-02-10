package radion.ru.srcm.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.collage")
@Getter
@Setter
public class ApiAppVar {
    private String baseUrl;
    private Endpoints endpoints = new Endpoints();

    @Setter
    @Getter
    public static class Endpoints{
        private String groups;
        private String weeks;
        private String teachers;
        private String subjects;
    }
}
