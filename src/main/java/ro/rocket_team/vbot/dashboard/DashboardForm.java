package ro.rocket_team.vbot.dashboard;


import java.io.Serializable;

class DashboardForm implements Serializable {

    private Boolean cameraEnabled;
    private String broadcastURL;

    DashboardForm(final Boolean cameraEnabled, final String broadcastURL) {
        this.cameraEnabled = cameraEnabled;
        this.broadcastURL = broadcastURL;
    }

    public Boolean isCameraEnabled() {
        return cameraEnabled;
    }

    public String getBroadcastURL() {
        return broadcastURL;
    }
}
