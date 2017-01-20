package ro.rocket_team.vbot.raspberry.engine;


import com.pi4j.wiringpi.Gpio;
import ro.rocket_team.vbot.raspberry.hw.RaspberryPin;

final class Engine {

    Engine() {
        Gpio.wiringPiSetupGpio();
        Gpio.pinMode(RaspberryPin.RIGHT_ENGINE_PWM, Gpio.PWM_OUTPUT);
        Gpio.pinMode(RaspberryPin.LEFT_ENGINE_PWM, Gpio.PWM_OUTPUT);


        Gpio.pwmSetMode(Gpio.PWM_MODE_MS);

        Gpio.pwmSetRange(480);
        Gpio.pwmSetClock(2);

        Gpio.pinMode(RaspberryPin.RIGHT_ENGINE_GPIO, Gpio.OUTPUT);
        Gpio.pinMode(RaspberryPin.LEFT_ENGINE_GPIO, Gpio.OUTPUT);
    }

    void setSpeed(final EngineSpeed<Integer, Integer> speed) {
        int directionLeft = 0;
        int currentLeftSpeed;
        if (speed.getLeftSpeed() < 0) {
            directionLeft = 1;
            currentLeftSpeed = -speed.getLeftSpeed();
        } else {
            currentLeftSpeed = speed.getLeftSpeed();
        }

        int directionRight = 0;
        int currentRightSpeed;
        if (speed.getRightSpeed() < 0) {
            directionRight = 1;
            currentRightSpeed = -speed.getRightSpeed();

        } else {
            currentRightSpeed = speed.getRightSpeed();
        }

        Gpio.digitalWrite(RaspberryPin.RIGHT_ENGINE_GPIO, directionRight);
        Gpio.digitalWrite(RaspberryPin.LEFT_ENGINE_GPIO, directionLeft);

        Gpio.pwmWrite(RaspberryPin.RIGHT_ENGINE_PWM, currentRightSpeed);
        Gpio.pwmWrite(RaspberryPin.LEFT_ENGINE_PWM, currentLeftSpeed);
    }

    void stop() {
        Gpio.pwmWrite(RaspberryPin.RIGHT_ENGINE_PWM, 0);
        Gpio.pwmWrite(RaspberryPin.LEFT_ENGINE_PWM, 0);
    }
}
