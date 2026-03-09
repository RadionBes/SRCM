package radion.ru.srcm.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radion.ru.srcm.dto.request.StudentCreateRequest;
import radion.ru.srcm.dto.request.StudentUpdateRequest;
import radion.ru.srcm.dto.response.StudentResponse;
import radion.ru.srcm.service.StudentService;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(
                studentService.getById(id)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(
            @RequestBody @Valid StudentCreateRequest studentCreateRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                studentService.create(studentCreateRequest)
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
