package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.logging.Loggable;
import radion.ru.srcm.mapper.response.GroupMapperResponse;
import radion.ru.srcm.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupsController {
    private final GroupService groupService;

    @GetMapping
    @Loggable(value = "Сетевой запрос", logResult = false, logParams = false)
    public List<GroupResponse> getAllGroups(){
        return groupService.getGroupList();
    }
}
