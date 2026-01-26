package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.dto.response.SubjectResponse;
import radion.ru.srcm.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/sync")
    public void syncSubjects(){
        subjectService.syncAllGroup();
    }

    @GetMapping
    public List<SubjectResponse> getSubjectById(@RequestParam("id") Long id){
        return subjectService.getAllSubjectById(id);
    }
}
