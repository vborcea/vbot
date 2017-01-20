package ro.rocket_team.vbot.raspberry.camera;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ro.rocket_team.vbot.webcamera.exception.BroadcastException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Component
public class VideoCamera {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoCamera.class);
    private static boolean isBroadCasting;

    private final String startCamera;
    private final String stopCamera;
    private final String checkCamera;

    @Autowired
    public VideoCamera(@Value("${camera.command.start_broadcast}") final String startCamera,
                       @Value("${camera.command.stop_broadcast}") final String stopCamera,
                       @Value("${camera.command.check_boradcast}") final String checkCamera) {
        this.startCamera = Objects.requireNonNull(startCamera, "startCamera must not be null");
        this.stopCamera = Objects.requireNonNull(stopCamera, "stopCamera must not be null");
        this.checkCamera = Objects.requireNonNull(checkCamera, "checkCamera must not be null");
    }

    public void startBroadCast() {
        executeCommand(startCamera);
        isBroadCasting = true;
    }

    private void executeCommand(final String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (final IOException e) {
            throw new BroadcastException("Failed to execute command: " + command, e);
        }
    }

    public void stopBroadCast() {
        executeCommand(stopCamera);
        isBroadCasting = false;
    }

    public boolean isBroadCasting() {
        return isBroadCasting;
    }

    public Boolean isCameraEnabledAndBroadcasting() {
        if (isCameraEnabled()) {
            return isBroadCasting;
        } else {
            return null;
        }
    }

    private boolean isCameraEnabled() {
        try {
            final Process exec = Runtime.getRuntime().exec(checkCamera);
            final StringBuilder sb = new StringBuilder();
            try (final InputStreamReader isr = new InputStreamReader(exec.getInputStream());
                 final BufferedReader br = new BufferedReader(isr)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
            return sb.toString().contains("supported=1 detected=1");
        } catch (final IOException e) {
            LOGGER.info("Something went wrong when trying to check the camera status: " + checkCamera, e);
            return false;
        }
    }

    public boolean notBroadCasting() {
        return !isBroadCasting;
    }
}
