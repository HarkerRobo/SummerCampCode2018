package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * 
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class RaiseArmManual extends Command {
    private double deadband;
    
    public RaiseArmManual(double deadband) {
        requires (Robot.getArm());
        this.deadband = deadband;
    }
    
    public void execute() {
        OI oi = OI.getInstance();
        
        double rightX = oi.getGamepad().getRightX();
        if (Math.abs(rightX) < deadband)
            rightX = 0;
        else
        {
            rightX = rightX -  Math.signum(rightX) * deadband;
            rightX /= 1- deadband;
        }
        Robot.getArm().setArmOutput(rightX);
    }

    /**
    * @return
    */
    @Override
    protected boolean isFinished () {
        return false;
    }
}
