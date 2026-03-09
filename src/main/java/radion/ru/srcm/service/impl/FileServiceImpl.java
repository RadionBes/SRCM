package radion.ru.srcm.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import radion.ru.srcm.config.PathAppVar;
import radion.ru.srcm.dao.FileJpaRepository;
import radion.ru.srcm.entity.File;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.entity.Student;
import radion.ru.srcm.exceptions.FileGetExtensionException;
import radion.ru.srcm.exceptions.FileWriteException;
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

    @Override
    @Transactional
    public void uploadFileGroup(MultipartFile multipartFile, Long groupId) {
        log.info("Начинаем загрузку файла для группы {}", groupId);
        Group group = groupService.getGroupById(groupId);

        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID() + fileExtension;
            String savePath = fileWriter(pathAppVar.getGroups(), group.getId().toString(), uniqueFileName, multipartFile);
            long id = fileJpaRepository.save(
                    File.builder()
                            .id(null)
                            .name(originalFilename)
                            .fileType(getFileExtension(originalFilename))
                            .size(String.valueOf(multipartFile.getSize()))
                            .filePath(savePath)
                            .group(group)
                            .student(null)
                            .build()
            ).getId();
            log.info("Файл {} для группы {} успешно сохранен", id, groupId);
        }catch (RuntimeException exception){
            log.error("Не получилось сохранить файл для группы {}.\n {}", groupId, exception.getMessage());
        }



    }
    @Override
    @Transactional
    public void uploadFileStudent(MultipartFile multipartFile, Long studentId) {
        log.info("Начинаем загрузку файла для студента {}", studentId);
        Student student = studentServiceOriginal.getById(studentId);
        try {
            String uniqueFileName = getUniqueName(multipartFile);
            fileWriter(pathAppVar.getStudents(), student.getId().toString(), uniqueFileName, multipartFile);
        } catch (RuntimeException exception){
            log.error("Не удалось сохранить файл для студента {}.\n {}", studentId, exception.getMessage());
        }

    }
    @Override
    public void copyFile(Long id) {}
    private String getUniqueName(MultipartFile multipartFile){
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            return UUID.randomUUID() + fileExtension;
        } catch (Exception e) {
            log.error("Не получилось обработать полное имя файла \n {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
    private String fileWriter(String path, String nameOfDirectory, String uniqueFileName, MultipartFile multipartFile){
        try {
            Path groupDir = Paths.get(path, nameOfDirectory);
            Files.createDirectories(groupDir);

            Path filePath = groupDir.resolve(uniqueFileName);
            Files.write(filePath, multipartFile.getBytes());
            return filePath.toString();
        } catch (IOException exception){
            log.error("Ошибка записи файла \n {}", exception.getMessage());
            throw new FileWriteException(exception.getMessage());
        }
    }
    private String getFileExtension(String filename) {
        try {
            if (filename == null || filename.lastIndexOf(".") == -1) {
                return "";
            }
            return filename.substring(filename.lastIndexOf(".") + 1);
        } catch (RuntimeException e) {
            log.error("Не удалось получить расширение файла \n {}", e.getMessage());
            throw new FileGetExtensionException(e.getMessage());
        }
    }
}
