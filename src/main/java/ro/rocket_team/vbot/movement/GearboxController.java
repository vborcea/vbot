package ro.rocket_team.vbot.movement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.rocket_team.vbot.raspberry.engine.Direction;
import ro.rocket_team.vbot.raspberry.engine.GearBox;

import java.io.IOException;

@Controller
public class GearboxController {

    @RequestMapping("/accelerate")
    public ResponseEntity accelerate(final String gear, final String direction) throws IOException {
        GearBox.valueOf(gear).accelerate(Direction.valueOf(direction));
        return ResponseEntity.ok("");
    }
}
