package radion.ru.srcm.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import radion.ru.srcm.service.GroupService;
import radion.ru.srcm.service.TeacherService;
import radion.ru.srcm.service.WeekService;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppStartupRunner {
    private final WeekService weekService;
    private final GroupService groupService;
    private final TeacherService teacherService;

    @EventListener(ApplicationReadyEvent.class)
    @Async
    public void runAfterStartup(){
        log.info("Запуск синхронизации после старта приложения");
        try {
            weekService.sync();
            groupService.sync();
            teacherService.sync();
            log.info("Синхронизация успешно завершена");
        } catch (Exception e) {
            log.error("Ошибка при синхронизации: {}", e.getMessage(), e);
        }
    }
}
