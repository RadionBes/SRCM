package radion.ru.srcm.web.singleControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.entity.Pair;
import radion.ru.srcm.logging.Loggable;
import radion.ru.srcm.service.PairService;

import java.util.List;

@RestController
@RequestMapping("/pairs")
@RequiredArgsConstructor
public class PairController {
    private final PairService pairService;

    @GetMapping
    @Loggable(value = "Сетевой запрос", logResult = false, logParams = false)
    public ResponseEntity<List<Pair>> getAllPair(){
        return ResponseEntity.ok(pairService.getAll());
    }
}
