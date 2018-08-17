package frc.robot;

import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper;

import frc.commands.MoveArmPosition;
import frc.subsystems.Arm.ArmDirection;

/**
 * Represents all the IO constants/operations for the robots.
 * 
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class OI {
    public static OI oi;
    private static final int DRIVER_PORT = 0;

    private GamepadWrapper gamepad = new GamepadWrapper(DRIVER_PORT);
    public static final double CONTROLLER_JOYSTICK_DEADBAND = 0.1;
    public static final double CONTROLLER_TRIGGER_DEADBAND = 0.3;

    /**
     * Gets the gamepad.
     * @return the gamepad
     */
    public GamepadWrapper getGamepad () {
        return gamepad;
    }

    /**
     * Gets the current instance of OI, creating a new one if necessary.
     * @return the current instance of OI
     */
    public static OI getInstance () {
        if (oi == null) {
            oi = new OI();
        }
        return oi;
    }

    /**
     * Constructs a new OI.
     */
    public OI () { 
        initBindings();
    }
    
    /**
     * Initializes the bindings on the gamepad to correspond to commands.
     */
    private void initBindings () { 
        gamepad.getButtonY().whenPressed(new MoveArmPosition (ArmDirection.UP));
        gamepad.getButtonA().whenPressed(new MoveArmPosition (ArmDirection.DOWN));
    } 
} 