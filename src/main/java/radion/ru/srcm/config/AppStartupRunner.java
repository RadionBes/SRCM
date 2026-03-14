package radion.ru.srcm.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import radion.ru.srcm.logging.Loggable;
import radion.ru.srcm.service.*;

@Component
@RequiredArgsConstructor
public class AppStartupRunner {
    private final WeekService weekService;
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final RoomService roomService;
    private final PairService pairService;
    private final SubjectService subjectService;

    @EventListener(ApplicationReadyEvent.class)
    @Loggable(value = "Синхронизация сервиса")
    public void runAfterStartup() throws Exception {
        try {
            groupService.sync();
            pairService.sync();
            roomService.sync();
            weekService.sync();
            teacherService.sync();
            subjectService.syncAllGroup();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
