package ro.rocket_team.vbot.raspberry.engine;

import java.util.Objects;

public class EngineSpeed<T1, T2> {

    private int leftSpeed;
    private int rightSpeed;

    public EngineSpeed(final T1 t1, final T2 t2) {
        leftSpeed = (Integer) Objects.requireNonNull(t1, "Left engine speed must not be null");
        rightSpeed = (Integer) Objects.requireNonNull(t2, "Right engine speed must not be null");
    }

    public int getLeftSpeed() {
        return leftSpeed;
    }

    public int getRightSpeed() {
        return rightSpeed;
    }
}
