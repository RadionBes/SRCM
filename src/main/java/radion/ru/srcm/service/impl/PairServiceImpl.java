package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dao.PairJpaRepository;
import radion.ru.srcm.entity.Pair;
import radion.ru.srcm.logging.Loggable;
import radion.ru.srcm.mapper.entity.PairMapperEntity;
import radion.ru.srcm.service.PairService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PairServiceImpl implements PairService {
    private final MapApiCollageService mapApiCollageService;
    private final PairJpaRepository pairJpaRepository;
    private final PairMapperEntity pairMapperEntity;

    @Override
    @Loggable(value = "Синхронизация времени начала пар", logParams = false)
    public void sync() {
        pairJpaRepository.saveAll(
                pairMapperEntity.toEntity(
                        mapApiCollageService.getListPairs()
                )
        );
    }

    @Override
    @Loggable(value = "Получение времени начала пар", logParams = false)
    public List<Pair> getAll() {
        return pairJpaRepository.findAll();
    }
}
