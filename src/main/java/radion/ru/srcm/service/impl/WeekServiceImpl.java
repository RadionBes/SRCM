package radion.ru.srcm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dao.WeekJpaRepository;
import radion.ru.srcm.entity.Week;
import radion.ru.srcm.service.WeekService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeekServiceImpl implements WeekService {
    private final WeekJpaRepository weekJpaRepository;
    private final MapApiCollageService mapApiCollageService;

    @Override
    public void sync() {
        var list = mapApiCollageService.getListWeeks();
        List<Week> weeks = list.stream()
                .map(el -> Week.builder().id(null).date(
                        LocalDate.parse(el)
                ).build())
                .toList();
        weekJpaRepository.saveAll(weeks);
        log.info("Sync weeks. Saves at {} week element successful.", weeks.size());
    }

    @Override
    public List<Week> getAll() {
        return weekJpaRepository.findAll();
    }
}
