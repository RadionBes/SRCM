package radion.ru.srcm.apiCollage.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.config.ApiAppVar;
import radion.ru.srcm.dto.api.*;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapApiCollageServiceImpl implements MapApiCollageService {
    private final ApiAppVar apiAppVar;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    @Override
    public List<GroupApiDto> getListGroup() {
        var request = new Request.Builder()
                .url(apiAppVar.getBaseUrl() + apiAppVar.getEndpoints().getGroups())
                .get()
                .build();
        try (var response = client.newCall(request).execute()) {
            return objectMapper.readValue(response.body().string(), new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SubjectApiDto> getListSubjectForGroup(String key) {
        var request = new Request.Builder()
                .url(apiAppVar.getBaseUrl() + apiAppVar.getEndpoints().getSubjects() + "?group=" + Long.parseLong(key))
                .get()
                .build();
        try (var response = client.newCall(request).execute()) {
            return objectMapper.readValue(response.body().string(), new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getListWeeks() {
        var request = new Request.Builder()
                .url(apiAppVar.getBaseUrl() + apiAppVar.getEndpoints().getWeeks())
                .get()
                .build();
        try (var response = client.newCall(request).execute()) {
            return objectMapper.readValue(response.body().string(), new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PairApiDto> getListPairs() {
        return List.of();
    }

    @Override
    public List<RoomApiDto> getListRooms() {
        return List.of();
    }

    @Override
    public List<TeacherApiDto> getListTeachers() {
        var request = new Request.Builder()
                .url(apiAppVar.getBaseUrl() + apiAppVar.getEndpoints().getTeachers())
                .get()
                .build();
        try (var response = client.newCall(request).execute()) {
            return objectMapper.readValue(response.body().string(), new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TimetableTeacherApiDto> getListTimetableTeacher() {
        return List.of();
    }
}