package radion.ru.srcm.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void uploadFileGroup(MultipartFile multipartFile, Long groupId);
    void uploadFileStudent(MultipartFile multipartFile, Long studentId);
    void copyFile(Long id);
}
