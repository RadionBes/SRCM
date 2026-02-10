package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import radion.ru.srcm.config.PathAppVar;
import radion.ru.srcm.dao.FileJpaRepository;
import radion.ru.srcm.entity.File;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.service.FileService;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final PathAppVar pathAppVar;
    private final FileJpaRepository fileJpaRepository;
    private final GroupService groupService;
    private final StudentService studentService;

    @Override
    public void uploadFile(MultipartFile multipartFile, Long groupId, Long studentId) throws IOException {
        if (groupId != null){
            Group group = groupService.getGroupById(groupId);
            // Генерируем уникальное имя файла
            String originalFilename = multipartFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            // Создаем директорию для группы, если её нет
            Path groupDir = Paths.get(pathAppVar.getGroups(), String.valueOf(group.getId()));
            Files.createDirectories(groupDir);

            // Сохраняем файл на диск
            Path filePath = groupDir.resolve(uniqueFileName);
            Files.write(filePath, multipartFile.getBytes());
        } else if (studentId != null){

        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void copyFile(Long id) {

    }
}
