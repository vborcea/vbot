package ro.rocket_team.vbot.raspberry.engine;


public final class EngineIgnition {
    private static EngineIgnition instance;
    private static Engine engine;

    private EngineIgnition() {
        // no initialization
    }

    public static EngineIgnition initialize() {
        if (instance == null) {
            synchronized (EngineIgnition.class) {
                if (instance == null) {
                    engine = new Engine();
                    instance = new EngineIgnition();
                }
            }
        }
        return instance;
    }

    public Engine get() {
        return engine;
    }
}
