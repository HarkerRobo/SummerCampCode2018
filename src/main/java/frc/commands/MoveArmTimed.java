package frc.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ArmConstants;
import frc.subsystems.Arm.ArmDirection;

/**
 * Moves the arm for a given amount of time.
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class MoveArmTimed extends TimedCommand {

    private ArmDirection direction;

    /**
     * Constructs a new RaiseArmTimed.
     * @param timeout the time for which the arm should be raised
     */
    public MoveArmTimed (double timeout, ArmDirection direction) {
        super(timeout);
        requires(Robot.getArm());
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    public void execute () {
        Robot.getArm().setArmOutput((direction == ArmDirection.UP ? ArmConstants.UP_SIGN : -ArmConstants.UP_SIGN)
                * ArmConstants.TIMED_OUTPUT);
    }
    
    /**
     * {@inheritDoc}
     */
    public void end () {
        Robot.getArm().setArmOutput(0);
    }

}
