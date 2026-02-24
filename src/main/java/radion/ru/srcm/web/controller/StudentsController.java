package radion.ru.srcm.web.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radion.ru.srcm.dto.request.StudentCreateRequest;
import radion.ru.srcm.dto.request.StudentUpdateRequest;
import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.dto.request.StudentsCreateRequest;
import radion.ru.srcm.dto.response.StudentResponse;
import radion.ru.srcm.mapper.response.GroupMapperResponse;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {
    private final GroupService groupService;
    private final StudentService studentService;
    private final GroupMapperResponse groupMapperResponse;

    @PostMapping
    private ResponseEntity<GroupResponse> createStudents(@RequestBody StudentsCreateRequest request) {
        studentService.createStudentsFromList(request);
        return ResponseEntity.ok(
                groupMapperResponse.toResponse(
                        groupService.getGroupById(request.getGroupId())
                )
        );
    }

    @GetMapping
    private List<StudentResponse> getAllStudent() {
        return studentService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(
                studentService.getById(id)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(
            @RequestParam("groupId") Long groupId,
            @RequestBody StudentCreateRequest studentCreateRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                studentService.create(studentCreateRequest, groupId)
        );
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody @Valid StudentUpdateRequest studentUpdateRequest){
        return ResponseEntity.ok(
                studentService.update(
                        studentUpdateRequest
                )
        );
    }
}