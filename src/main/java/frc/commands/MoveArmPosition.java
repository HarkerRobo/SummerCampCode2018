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
    
    public MoveArmPosition (ArmDirection direction) {
        this.direction = direction;
    }
    
    public void execute () { 
        Robot.getArm().setArmOutput((direction == ArmDirection.UP ? ArmConstants.UP_SIGN : -ArmConstants.UP_SIGN)
                * ArmConstants.TIMED_OUTPUT);
    }
    
    public void end () {
        Robot.getArm().setArmOutput(0);
    }
    /**
    * @return
    */
    @Override
    protected boolean isFinished () {
        // TODO Auto-generated method stub
        return Robot.getArm().getArmTalon().getOutputCurrent() >= ArmConstants.CURRENT_SPIKE;
    }
    
}
