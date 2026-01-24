package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.dto.request.StudentsCreateRequest;
import radion.ru.srcm.dto.response.StudentsResponse;
import radion.ru.srcm.mapper.GroupMapper;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsController {
    private final GroupService groupService;
    private final StudentService studentService;
    private final GroupMapper groupMapper;

    @PostMapping
    private ResponseEntity<GroupResponse> createStudents(@RequestBody StudentsCreateRequest request) {
        studentService.createStudentsFromList(request);
        return ResponseEntity.ok(
                groupMapper.toDtoResponse(
                        groupService.getGroupById(request.getGroupId())
                )
        );
    }

    @GetMapping
    private StudentsResponse getAllStudent() {
        return studentService.getAll();
    }
}