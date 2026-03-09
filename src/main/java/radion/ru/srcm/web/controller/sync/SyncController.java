package radion.ru.srcm.web.controller.sync;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.WeekService;

@RestController
@RequestMapping("/sync")
@RequiredArgsConstructor
public class SyncController {
    private final WeekService weekService;
    private final GroupService groupService;

    @GetMapping("/weeks")
    public void syncWeeks(){
        weekService.sync();
    }

    @GetMapping("/group")
    public void syncGroup(){
        groupService.sync();
    }

    @GetMapping("/teacher")
    public void syncTeacher(){
        groupService.sync();
    }

}