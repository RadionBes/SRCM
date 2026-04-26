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

/**
 * Компонент, выполняющий начальную синхронизацию данных сразу после запуска приложения.
 * <p>
 * Реагирует на событие {@link ApplicationReadyEvent} – это означает, что контекст приложения
 * полностью загружен и все бины инициализированы, а веб-сервер готов принимать запросы.
 * </p>
 * <p>
 * В рамках синхронизации последовательно вызываются методы sync() у сервисов групп, пар, аудиторий,
 * недель, преподавателей, а также метод syncAllGroup() у сервиса предметов.
 * </p>
 *
 * @author RadionBes
 * @see ApplicationReadyEvent
 */

@Component
@RequiredArgsConstructor
public class AppStartupRunner {
    private final WeekService weekService;
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final RoomService roomService;
    private final PairService pairService;
    private final SubjectService subjectService;


    /**
     * Запускает синхронизацию всех основных сущностей (группы, пары, аудитории, недели, преподаватели, предметы).
     * <p>
     * Метод вызывается автоматически после того, как приложение полностью готово к работе.
     * Любое возникшее исключение оборачивается в {@link Exception} и пробрасывается наверх,
     * что может привести к остановке контекста (в зависимости от политики обработки ошибок).
     * </p>
     * <p>
     <b>Примечание по текущей реализации:</b> обёртка в {@code throw new Exception(e)} является избыточной,
     * так как исходное исключение просто перебрасывается. Рекомендуется заменить на {@code throw e}
     * или обработать логирование с последующим пробросом.
     * </p>
     *
     * @throws Exception если любой из вызванных методов синхронизации завершился ошибкой
     * @see GroupService#sync()
     * @see PairService#sync()
     * @see RoomService#sync()
     * @see WeekService#sync()
     * @see TeacherService#sync()
     * @see SubjectService#syncAllGroup()
     */
    @EventListener(ApplicationReadyEvent.class)
    @Loggable(value = "Синхронизация сервиса", logParams = false, logResult = false)
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
