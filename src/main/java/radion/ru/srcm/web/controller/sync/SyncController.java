package radion.ru.srcm.web.controller.sync;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.logging.Loggable;
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
    @Loggable(value = "Сетевой запрос", logResult = false, logParams = false)
    public void syncWeeks(){
        weekService.sync();
    }

    @GetMapping("/groups")
    @Loggable(value = "Сетевой запрос", logResult = false, logParams = false)
    public void syncGroups(){
        groupService.sync();
    }

    @GetMapping("/teachers")
    @Loggable(value = "Сетевой запрос", logResult = false, logParams = false)
    public void syncTeachers(){groupService.sync();}

    @GetMapping("/rooms")
    @Loggable(value = "Сетевой запрос", logResult = false, logParams = false)
    public void syncRooms(){roomService.sync();}

    @GetMapping("/pairs")
    @Loggable(value = "Сетевой запрос", logResult = false, logParams = false)
    public void syncPairs(){pairService.sync();}

}