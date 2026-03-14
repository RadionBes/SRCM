package radion.ru.srcm.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import radion.ru.srcm.config.PathAppVar;
import radion.ru.srcm.dao.FileJpaRepository;
import radion.ru.srcm.entity.File;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.entity.Student;
import radion.ru.srcm.exceptions.FileGetExtensionException;
import radion.ru.srcm.exceptions.FileWriteException;
import radion.ru.srcm.logging.Loggable;
import radion.ru.srcm.service.FileService;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.entity.StudentServiceOriginal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {
    private final PathAppVar pathAppVar;
    private final FileJpaRepository fileJpaRepository;
    private final GroupService groupService;
    private final StudentServiceOriginal studentServiceOriginal;
    private final MessageSource messageSource;

    @Override
    @Transactional
    @Loggable(value = "Загрузка файла для группы", logResult = false)
    public void uploadFileGroup(MultipartFile multipartFile, Long groupId) {
        Group group = groupService.getGroupById(groupId);

        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID() + fileExtension;
            String savePath = fileWriter(pathAppVar.getGroups(), group.getId().toString(), uniqueFileName, multipartFile);
            fileJpaRepository.save(
                    File.builder()
                            .id(null)
                            .name(originalFilename)
                            .fileType(getFileExtension(originalFilename))
                            .size(String.valueOf(multipartFile.getSize()))
                            .filePath(savePath)
                            .group(group)
                            .student(null)
                            .build()
            );
        } catch (Exception e) {
            throw new FileWriteException(e.getMessage());
        }


    }

    @Override
    @Transactional
    @Loggable(value = "Загрузка файла для студента", logResult = false)
    public void uploadFileStudent(MultipartFile multipartFile, Long studentId) {
        Student student = studentServiceOriginal.getById(studentId);

        try {
            String uniqueFileName = getUniqueName(multipartFile);
            fileWriter(pathAppVar.getStudents(), student.getId().toString(), uniqueFileName, multipartFile);
        } catch (RuntimeException e) {
            throw new FileWriteException(e.getMessage());
        }

    }
    @Override
    public void copyFile(Long id) {}

    @Loggable(value = "Получение уникального имени файла", logResult = false)
    private String getUniqueName(MultipartFile multipartFile) throws RuntimeException {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            return UUID.randomUUID() + fileExtension;
        } catch (Exception e) {
            throw new RuntimeException(
                    messageSource.getMessage(
                            "error.FieldGetFullFileName",
                            null,
                            null
                    )
            );
        }
    }

    @Loggable(value = "Запись файла", logResult = false)
    private String fileWriter(String path, String nameOfDirectory, String uniqueFileName, MultipartFile multipartFile) throws RuntimeException {
        try {
            Path groupDir = Paths.get(path, nameOfDirectory);
            Files.createDirectories(groupDir);

            Path filePath = groupDir.resolve(uniqueFileName);
            Files.write(filePath, multipartFile.getBytes());
            return filePath.toString();
        } catch (IOException exception){
            throw new RuntimeException(
                    messageSource.getMessage(
                            "error.FileWriteException",
                            null,
                            null
                    )
            );
        }
    }

    @Loggable(value = "Получение расширения файла", logResult = false)
    private String getFileExtension(String filename) throws FileGetExtensionException{
        try {
            if (filename == null || filename.lastIndexOf(".") == -1) {
                return "";
            }
            return filename.substring(filename.lastIndexOf(".") + 1);
        } catch (RuntimeException e) {
            throw new FileGetExtensionException(
                    messageSource.getMessage(
                            "error.FileGetExtensionException",
                            null,
                            null
                    )
            );
        }
    }
}
