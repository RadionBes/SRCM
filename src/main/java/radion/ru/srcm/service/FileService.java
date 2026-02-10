package radion.ru.srcm.service;

import org.springframework.web.multipart.MultipartFile;
import radion.ru.srcm.entity.File;

import java.io.IOException;

public interface FileService {
    public void uploadFile(MultipartFile multipartFile, Long groupId, Long studentId) throws IOException;
    void copyFile(Long id);

}
