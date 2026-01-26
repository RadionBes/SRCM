package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.dto.response.SubjectResponse;
import radion.ru.srcm.mapper.response.GroupMapperResponse;
import radion.ru.srcm.service.GroupService;

import java.util.Comparator;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final GroupMapperResponse mapperResponse;

    @GetMapping
    public GroupResponse getGroupById(@RequestParam("id") Long id) {
        return mapperResponse.toResponse(
                groupService.getGroupById(id)
        );
    }
}
