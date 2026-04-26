package radion.ru.srcm.apiCollage;

import radion.ru.srcm.dto.api.*;
import radion.ru.srcm.logging.Loggable;

import java.util.List;

/**
 * Интерфейс, определяющий набор методов для получения данных с сервера колледжа.
 * <p>
 * Все методы предполагают синхронное выполнение и могут генерировать сетевые исключения
 * (например, {@link java.io.IOException}) в случае проблем с соединением или ошибках на стороне сервера.
 * Реализации обязаны корректно обрабатывать null-значения и пустые списки.
 * </p>
 *
 * @author RadionBes
 */
public interface MapApiCollageService {
    /**
     * Возвращает список всех групп, доступных в колледже.
     *
     * @return список объектов {@link GroupApiDto}, никогда не null (может быть пустым, если группы отсутствуют)
     * @throws RuntimeException если произошла ошибка при выполнении запроса (реализация может выбрасывать конкретное исключение)
     */
    List<GroupApiDto> getListGroup();

    /**
     * Возвращает список учебных предметов для указанной группы.
     *
     * @param key идентификатор группы внутри сервера колледжа (не должен быть null или пустым)
     * @return список объектов {@link SubjectApiDto} для данной группы, не null (может быть пустым)
     * @throws IllegalArgumentException если key равен null или пустой строке
     * @throws RuntimeException        если группа не найдена или произошла ошибка при запросе
     */
    List<SubjectApiDto> getListSubjectForGroup(String key);

    /**
     * Возвращает список учебных недель (например, номера или названия периодов).
     *
     * @return список строк, представляющих недели, не null (может быть пустым)
     */
    List<String> getListWeeks();

    /**
     * Возвращает список всех пар (занятий) в расписании.
     *
     * @return список объектов {@link PairApiDto}, не null (может быть пустым)
     */
    List<PairApiDto> getListPairs();

    /**
     * Возвращает список всех аудиторий (кабинетов) колледжа.
     *
     * @return список объектов {@link RoomApiDto}, не null (может быть пустым)
     */
    List<RoomApiDto> getListRooms();

    /**
     * Возвращает список всех преподавателей.
     *
     * @return список объектов {@link TeacherApiDto}, не null (может быть пустым)
     */
    List<TeacherApiDto> getListTeachers();

    /**
     * Возвращает расписание занятий для преподавателя.
     *
     * @param teacherKey строковое представление ключа преподавателя
     * @param week строковое представление даты начала недели в формате <b>"ГГГГ.ДД.ММ"</b>
     * @return список объектов {@link TimetableTeacherApiDto}, не null (может быть пустым)
     */
    List<TimetableTeacherApiDto> getListTimetableTeacher(String teacherKey, String week);
}