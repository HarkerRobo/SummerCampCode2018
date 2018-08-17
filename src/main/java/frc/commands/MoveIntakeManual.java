package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Moves the intake with a given percent output manually.
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class MoveIntakeManual extends Command {

    private double deadband;
    
    /**
     * Constructs a new MoveIntakeManual.
     * @param deadband the amount below which all trigger input will be treated as noise and ignored
     */
    public MoveIntakeManual (double deadband) {
        requires (Robot.getIntake());
        this.deadband = deadband;
    }
    
    /**
     * {@inheritDoc}
     */
    public void execute () {
        OI oi = OI.getInstance();
        double leftTrigger = oi.getGamepad().getLeftTrigger(); // intake
        double rightTrigger = -oi.getGamepad().getRightTrigger(); // outtake
        
        if (leftTrigger < deadband) {leftTrigger = 0;}
        if (rightTrigger < deadband) {rightTrigger = 0;}
        
        double output;
        if (Math.abs(leftTrigger) > Math.abs(rightTrigger))
            output = leftTrigger;
        else
            output = rightTrigger;
        
        Robot.getIntake().setIntakePercent(output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isFinished () {
        // TODO Auto-generated method stub
        return false;
    }

}
