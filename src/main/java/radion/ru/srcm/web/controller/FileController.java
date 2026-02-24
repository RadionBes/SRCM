package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import radion.ru.srcm.service.FileService;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/group")
    public void saveFileGroup(
            @RequestParam("groupId") Long groupId,
            MultipartFile multipartFile
    ){
        fileService.uploadFileGroup(multipartFile, groupId);
    }

    @PostMapping("/student")
    public void saveFileStudent(
            @RequestParam("studentId") Long studentId,
            MultipartFile multipartFile
    ){
        fileService.uploadFileStudent(multipartFile, studentId);
    }
}
