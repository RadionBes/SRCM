package radion.ru.srcm.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.apiCollage.MapApiCollageService;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final MapApiCollageService mapApiCollageService;

    @GetMapping("/")
    public void nn(){
        mapApiCollageService.getListGroup();
    }
}
