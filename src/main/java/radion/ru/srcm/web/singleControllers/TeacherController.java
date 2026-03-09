package radion.ru.srcm.web.singleControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.entity.Teacher;
import radion.ru.srcm.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        return ResponseEntity.ok(teacherService.getAll());
    }
}
