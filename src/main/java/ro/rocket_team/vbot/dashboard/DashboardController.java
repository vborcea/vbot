package ro.rocket_team.vbot.dashboard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.rocket_team.vbot.raspberry.camera.VideoCamera;

import java.util.Objects;

@Controller
public class DashboardController {

    private final VideoCamera videoCamera;
    private final String broadcastURL;

    @Autowired
    public DashboardController(@Value("${camera.broadcast.url}") final String broadcastURL,
                               final VideoCamera videoCamera) {
        this.broadcastURL = Objects.requireNonNull(broadcastURL, "broadcastURL must not be null.");
        this.videoCamera = Objects.requireNonNull(videoCamera, "videoCamera must not be null.");
    }

    @RequestMapping("/dashboardData")
    public ResponseEntity getStatus() {
        return ResponseEntity.ok(new DashboardForm(videoCamera.isCameraEnabledAndBroadcasting(), broadcastURL));
    }
}
