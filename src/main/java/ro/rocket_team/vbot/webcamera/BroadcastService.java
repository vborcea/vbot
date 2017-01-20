package ro.rocket_team.vbot.webcamera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.rocket_team.vbot.raspberry.camera.VideoCamera;

import java.util.Objects;

@Component
public class BroadcastService {

    private final VideoCamera videoCamera;

    @Autowired
    public BroadcastService(final VideoCamera videoCamera) {
        this.videoCamera = Objects.requireNonNull(videoCamera, "videoCamera must not be null.");
    }

    void start() {
        if (videoCamera.notBroadCasting()) {
            videoCamera.startBroadCast();
        }
    }

    void stop() {
        if (videoCamera.isBroadCasting()) {
            videoCamera.stopBroadCast();
        }
    }

    boolean isBroadCasting() {
        return videoCamera.isBroadCasting();
    }

}
