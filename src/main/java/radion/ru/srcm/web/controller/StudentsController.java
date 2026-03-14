package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.dto.request.StudentsCreateRequest;
import radion.ru.srcm.dto.response.StudentResponse;
import radion.ru.srcm.logging.Loggable;
import radion.ru.srcm.mapper.response.GroupMapperResponse;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentService studentService;

    @PostMapping
    @Loggable(value = "Сетевой запрос", logResult = false)
    private ResponseEntity<List<StudentResponse>> createStudents(@RequestBody StudentsCreateRequest request) {
        return ResponseEntity.ok(studentService.createStudentsFromList(request));
    }

    @GetMapping
    @Loggable(value = "Сетевой запрос", logResult = false, logParams = false)
    private List<StudentResponse> getAllStudent() {
        return studentService.getAll();
    }
}