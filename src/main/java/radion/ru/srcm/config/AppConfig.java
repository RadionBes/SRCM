package radion.ru.srcm.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AppConfig {
    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(30))
                .build();
    }
}
