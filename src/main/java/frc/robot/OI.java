package frc.robot;

import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper;

public class OI {
    private static final int DRIVER_PORT = 0;
    
    private GamepadWrapper gamepad = new GamepadWrapper(DRIVER_PORT);
    public static final double CONTROLLER_DEADBAND = 0.1;

    public GamepadWrapper getGamepad () {
        return gamepad;
    }

}