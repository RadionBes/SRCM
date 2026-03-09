package radion.ru.srcm.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import radion.ru.srcm.util.Interest;

import java.util.List;

@Data
@Builder
public class StudentsCreateRequest {
    @NotNull
    private Long groupId;
    @Valid
    private List<@Valid StudentRequestItem> students;


    @Data
    @Builder
    public static class StudentRequestItem{
        @NotBlank
        private String fullName;
        @NotBlank
        private String city;
        @NotNull
        private Interest interest;
        @Size(max = 1500)
        private String description;
    }
}
