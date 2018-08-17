package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap.ArmConstants;

/**
 * 
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class MoveArmManual extends Command {
    private double deadband;
    
    public MoveArmManual(double deadband) {
        requires (Robot.getArm());
        this.deadband = deadband;
    }
    
    public void execute() {
        OI oi = OI.getInstance();
        
        double rightY = oi.getGamepad().getRightY();
        if (Math.abs(rightY) < deadband)
            rightY = 0;
        else
        {
            rightY = rightY -  Math.signum(rightY) * deadband;
            rightY /= 1- deadband;
        }
        Robot.getArm().setArmOutput(ArmConstants.UP_SIGN * rightY);
    }

    /**
    * @return
    */
    @Override
    protected boolean isFinished () {
        return false;
    }
}
