package radion.ru.srcm.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import radion.ru.srcm.config.PathAppVar;
import radion.ru.srcm.dao.FileJpaRepository;
import radion.ru.srcm.entity.File;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.entity.Student;
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
public class FileServiceImpl implements FileService {
    private final PathAppVar pathAppVar;
    private final FileJpaRepository fileJpaRepository;
    private final GroupService groupService;
    private final StudentServiceOriginal studentServiceOriginal;

    @Override
    @Transactional
    public void uploadFileGroup(MultipartFile multipartFile, Long groupId) {
        Group group = groupService.getGroupById(groupId);

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
    }
    @Override
    @Transactional
    public void uploadFileStudent(MultipartFile multipartFile, Long studentId) {
        Student student = studentServiceOriginal.getById(studentId);
        String uniqueFileName = getUniqueName(multipartFile);
        fileWriter(pathAppVar.getStudents(), student.getId().toString(), uniqueFileName, multipartFile);
    }
    @Override
    public void copyFile(Long id) {

    }
    private String getUniqueName(MultipartFile multipartFile){
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return UUID.randomUUID() + fileExtension;
    }
    private String fileWriter(String path, String nameOfDirectory, String uniqueFileName, MultipartFile multipartFile){
        try {
            Path groupDir = Paths.get(path, nameOfDirectory);
            Files.createDirectories(groupDir);

            Path filePath = groupDir.resolve(uniqueFileName);
            Files.write(filePath, multipartFile.getBytes());
            return filePath.toString();
        } catch (IOException exception){
            throw new RuntimeException("Error directory or file created!");
        }
    }
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
