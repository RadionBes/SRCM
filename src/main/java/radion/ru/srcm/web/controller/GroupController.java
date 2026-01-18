package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import radion.ru.srcm.dto.GroupResponse;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.mapper.GroupMapper;
import radion.ru.srcm.service.GroupService;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final GroupMapper mapper;

    @GetMapping
    public GroupResponse getGroupInfoById(@RequestParam("id") Long id) {
        return mapper.toDtoResponse(
                groupService.getGroupInfo(id)
        );
    }
}
