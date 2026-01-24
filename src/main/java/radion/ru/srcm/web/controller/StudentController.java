package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radion.ru.srcm.dto.request.StudentCreateRequest;
import radion.ru.srcm.dto.request.StudentUpdateRequest;
import radion.ru.srcm.dto.response.StudentResponse;
import radion.ru.srcm.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<StudentResponse> getStudentById(@RequestParam("id") Long id){
        return ResponseEntity.ok(
                studentService.getById(id)
        );
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @RequestParam("groupId") Long groupId,
            @RequestBody StudentCreateRequest studentCreateRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                studentService.create(studentCreateRequest, groupId)
        );
    }

    @PatchMapping
    public ResponseEntity<?> updateStudent(@RequestBody StudentUpdateRequest studentUpdateRequest){
        return ResponseEntity.ok(
                studentService.update(
                        studentUpdateRequest
                )
        );
    }
}
