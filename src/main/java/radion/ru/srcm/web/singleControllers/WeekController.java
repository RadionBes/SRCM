package radion.ru.srcm.web.singleControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.entity.Week;
import radion.ru.srcm.service.WeekService;

import java.util.List;

@RestController
@RequestMapping("/weeks")
@RequiredArgsConstructor
public class WeekController {
    private final WeekService weekService;

    @GetMapping
    public ResponseEntity<List<Week>> getAllWeeks(){
        return ResponseEntity.ok(weekService.getAll());
    }
}
