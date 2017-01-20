package ro.rocket_team.vbot.raspberry.engine;

import ro.rocket_team.vbot.raspberry.engine.exceptions.DirectionException;

public enum Direction {
    LEFT,
    RIGHT,
    DEFAULT;

    public static Direction get(final String direction) {
        if (direction.equals("LEFT")) {
            return Direction.LEFT;
        } else if (direction.equals("RIGHT")) {
            return Direction.RIGHT;
        } else if (direction.equals("DEFAULT")) {
            return Direction.DEFAULT;
        } else throw new DirectionException("The direction: " + direction + " is not supported by the system");
    }
}