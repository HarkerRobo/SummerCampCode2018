package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap.ArmConstants;

/**
 * Moves the arm with a given percent output using manual control.
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class MoveArmManual extends Command {
    private double deadband;
    
    /**
     * Constructs a new MoveArmManual.
     * @param deadband the amount below which all joystick input will be treated as noise and ignored
     */
    public MoveArmManual(double deadband) {
        requires (Robot.getArm());
        this.deadband = deadband;
    }
    
    /**
     * {@inheritDoc}
     */
    public void execute() {
        OI oi = OI.getInstance();
        
        double rightY = oi.getGamepad().getRightY();
        if (Math.abs(rightY) < deadband || Robot.getArm().hasHitEnd())
            rightY = 0;
        else
        {
            rightY = rightY -  Math.signum(rightY) * deadband;
            rightY /= 1- deadband;
        }
        Robot.getArm().setArmOutput(ArmConstants.UP_SIGN * rightY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isFinished () {
        return false;
    }
}
