package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.logging.Loggable;
import radion.ru.srcm.mapper.response.GroupMapperResponse;
import radion.ru.srcm.service.GroupService;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final GroupMapperResponse mapperResponse;

    @GetMapping("/{id}")
    @Loggable(value = "Сетевой запрос", logResult = false)
    public GroupResponse getGroupById(@PathVariable Long id) {
        return mapperResponse.toResponse(
                groupService.getGroupById(id)
        );
    }

    @PatchMapping("/{id}/update")
    @Loggable(value = "Сетевой запрос", logResult = false)
    public ResponseEntity<String> updateGroup(@PathVariable Long id){

        return ResponseEntity.ok("Hehe :)");
    }
}
