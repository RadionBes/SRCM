package radion.ru.srcm.service;

import radion.ru.srcm.entity.Week;

import java.util.List;

public interface WeekService {
    void sync();

    List<Week> getAll();
}
