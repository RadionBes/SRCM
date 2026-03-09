package radion.ru.srcm.web.controller.sync;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.PairService;
import radion.ru.srcm.service.RoomService;
import radion.ru.srcm.service.WeekService;

@RestController
@RequestMapping("/sync")
@RequiredArgsConstructor
public class SyncController {
    private final WeekService weekService;
    private final GroupService groupService;
    private final RoomService roomService;
    private final PairService pairService;

    @GetMapping("/weeks")
    public void syncWeeks(){
        weekService.sync();
    }

    @GetMapping("/groups")
    public void syncGroups(){
        groupService.sync();
    }

    @GetMapping("/teachers")
    public void syncTeachers(){groupService.sync();}

    @GetMapping("/rooms")
    public void syncRooms(){roomService.sync();}

    @GetMapping("/pairs")
    public void syncPairs(){pairService.sync();}

}