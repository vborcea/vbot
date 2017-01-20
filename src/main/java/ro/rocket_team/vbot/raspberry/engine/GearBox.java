package ro.rocket_team.vbot.raspberry.engine;

import org.springframework.stereotype.Component;
import ro.rocket_team.vbot.raspberry.engine.exceptions.GearBoxException;

@Component
public enum GearBox {

    I {
        @Override
        public void accelerate(final Direction direction) {
            final int FIRST_GEAR_SPEED = 90;
            EngineIgnition.initialize().get().setSpeed(getEngineSpeed(FIRST_GEAR_SPEED, direction));
        }
    },
    II {
        @Override
        public void accelerate(final Direction direction) {
            final int SECOND_GEAR_SPEED = 140;
            EngineIgnition.initialize().get().setSpeed(getEngineSpeed(SECOND_GEAR_SPEED, direction));
        }
    },
    III {
        @Override
        public void accelerate(final Direction direction) {
            final int THIRD_GEAR_SPEED = 170;
            EngineIgnition.initialize().get().setSpeed(getEngineSpeed(THIRD_GEAR_SPEED, direction));
        }
    },
    IV {
        @Override
        public void accelerate(final Direction direction) {
            int FORTH_GEAR_SPEED = 200;
            EngineIgnition.initialize().get().setSpeed(getEngineSpeed(FORTH_GEAR_SPEED, direction));
        }
    },
    DEFAULT {
        @Override
        public void accelerate(final Direction direction) {
            EngineIgnition.initialize().get().stop();
        }
    },
    R {
        @Override
        public void accelerate(final Direction direction) {
            final int REVERSE_GEAR_SPEED = -120;
            EngineIgnition.initialize().get().stop();
            EngineIgnition.initialize().get().setSpeed(getEngineSpeed(REVERSE_GEAR_SPEED, direction));
        }
    };

    private static EngineSpeed<Integer, Integer> getEngineSpeed(final int gearSpeed,final Direction direction) {
        final int MULTIPLICATION_FACTOR = 1;
        if (direction == Direction.RIGHT) {
            return new EngineSpeed<>(gearSpeed * MULTIPLICATION_FACTOR, 0);
        } else if (direction == Direction.LEFT) {
            return new EngineSpeed<>(0, gearSpeed * MULTIPLICATION_FACTOR);
        } else if (direction == Direction.DEFAULT) {
            return new EngineSpeed<>(gearSpeed * MULTIPLICATION_FACTOR, gearSpeed * MULTIPLICATION_FACTOR);
        }

        throw new GearBoxException("The speed cannot be applied for gearbox: " + gearSpeed + " and direction: " + direction);
    }

    public abstract void accelerate(final Direction direction);
}
