package frc.robot;

import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper;

import frc.commands.MoveArmPosition;
import frc.subsystems.Arm.ArmDirection;

public class OI {
    public static OI oi;
    private static final int DRIVER_PORT = 0;

    private GamepadWrapper gamepad = new GamepadWrapper(DRIVER_PORT);
    public static final double CONTROLLER_JOYSTICK_DEADBAND = 0.1;
    public static final double CONTROLLER_TRIGGER_DEADBAND = 0.3;

    public GamepadWrapper getGamepad () {
        return gamepad;
    }

    public static OI getInstance () {
        if (oi == null) {
            oi = new OI();
        }
        return oi;
    }

    public OI () { 
        initBindings();
    }
    
    public void initBindings () { 
        gamepad.getButtonY().whenPressed(new MoveArmPosition (ArmDirection.UP));
        gamepad.getButtonA().whenPressed(new MoveArmPosition (ArmDirection.DOWN));
    } 
} 