package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.RaiseArmManual;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.RobotMap.*;

/**
 * 
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class Arm extends Subsystem {
    private static Arm arm; // singleton

    private TalonSRX armTalon;

    public Arm () {
        armTalon = new TalonSRX(CAN_IDs.ARM_TALON);
    }

    public void talonInit () {
        armTalon.setNeutralMode(ArmConstants.TALON_NEUTRAL_MODE);
        armTalon.setInverted(ArmConstants.INVERTED);
        setCurrentLimit(ArmConstants.PEAK_CURRENT_LIMIT, ArmConstants.PEAK_TIME_MS,
                ArmConstants.CONTINUOUS_CURRENT_LIMIT);
    }

    private void setCurrentLimit (int peakCurrentLimit, int peakTime, int continuousLimit) {
        armTalon.configPeakCurrentLimit(peakCurrentLimit, RobotMap.TIMEOUT);

        armTalon.configPeakCurrentDuration(peakTime, RobotMap.TIMEOUT);

        armTalon.configContinuousCurrentLimit(continuousLimit, RobotMap.TIMEOUT);

        armTalon.enableCurrentLimit(true);
    }

    /**
    * 
    */
    @Override
    protected void initDefaultCommand () {
        setDefaultCommand (new RaiseArmManual(OI.CONTROLLER_DEADBAND));
    }

    public static Arm getInstance () {
        if (arm == null) {
            arm = new Arm();
        }
        return arm;
    }

    private TalonSRX getArmTalon () {
        return armTalon;
    }
    
    public void setArmOutput (double output)
    {
        getArmTalon().set(ControlMode.PercentOutput, output);
    }

}
