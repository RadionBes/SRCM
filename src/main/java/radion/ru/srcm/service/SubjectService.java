package radion.ru.srcm.service;


import radion.ru.srcm.dto.response.SubjectResponse;

import java.util.List;

public interface SubjectService {
    List<SubjectResponse> getAllSubjectById(Long id);
    void syncAllGroup();
}
