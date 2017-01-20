package ro.rocket_team.vbot.webcamera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public class BroadcastController {

    private BroadcastService broadcastService;

    @Autowired
    public BroadcastController(final BroadcastService broadcastService) {
        this.broadcastService = Objects.requireNonNull(broadcastService, "broadcastService must not be null.");
    }

    @RequestMapping("/startbroadcast")
    public ResponseEntity startBroadcast() {
        broadcastService.start();
        return ResponseEntity.ok(broadcastService.isBroadCasting());
    }

    @RequestMapping("/stopbroadcast")
    public ResponseEntity stopBroadcast() {
        broadcastService.stop();
        return ResponseEntity.ok(broadcastService.isBroadCasting());
    }
}
