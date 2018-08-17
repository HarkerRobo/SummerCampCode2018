package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ArmConstants;
import frc.subsystems.Arm.ArmDirection;

/**
 * 
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class MoveArmPosition extends Command {

    private ArmDirection direction;
    
    /**
     * Constructs a new MoveArmPosition.
     * @param direction the direction in which the arm should move
     */
    public MoveArmPosition (ArmDirection direction) {
        requires (Robot.getArm());
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isFinished () {
        return Robot.getArm().hasHitEnd();
    }
    
}
