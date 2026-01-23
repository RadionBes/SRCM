package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.dto.GroupResponse;
import radion.ru.srcm.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupsController {
    private final GroupService groupService;

    @GetMapping
    public List<GroupResponse> getAllGroups(){
        return groupService.getGroupList();
    }
}
