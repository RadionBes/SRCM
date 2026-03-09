package radion.ru.srcm.service;

import radion.ru.srcm.entity.Pair;

import java.util.List;

public interface PairService {
    void sync();

    List<Pair> getAll();
}
