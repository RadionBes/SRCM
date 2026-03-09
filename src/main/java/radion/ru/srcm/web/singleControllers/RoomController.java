package radion.ru.srcm.web.singleControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radion.ru.srcm.entity.Room;
import radion.ru.srcm.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
        return ResponseEntity.ok(roomService.getAll());
    }
}
