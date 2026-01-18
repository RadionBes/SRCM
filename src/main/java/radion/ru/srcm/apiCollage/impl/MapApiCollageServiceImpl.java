package radion.ru.srcm.apiCollage.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.config.AppVar;
import radion.ru.srcm.dto.GroupApiDto;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapApiCollageServiceImpl implements MapApiCollageService {
    private final AppVar appVar;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    @Override
    public void getListGroup() {
        var request = new Request.Builder()
                .url(appVar.getBaseUrl() + appVar.getEndpoints().getGroups())
                .get()
                .build();
        try (var response = client.newCall(request).execute()) {
            List<GroupApiDto> body = objectMapper.readValue(response.body().string(), new TypeReference<>(){});
            System.out.println(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}