package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.MoveArmManual;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.*;

/**
 * Represents the arm on the robot.
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class Arm extends Subsystem {
    private static Arm arm; // singleton

    private TalonSRX armTalon;
    
    /**
     * Represents which way the arm will move (up corresponds to positive output, down to negative).
     * @author Finn Frankis
     * @version Aug 16, 2018
     */
    public enum ArmDirection {
        UP, DOWN
    }

    /**
     * Constructs a new Arm.
     */
    public Arm () {
        armTalon = new TalonSRX(CAN_IDs.ARM_TALON);
    }

    /**
     * Calls all important initialization code on the arm talons.
     */
    public void talonInit () {
        armTalon.setNeutralMode(ArmConstants.TALON_NEUTRAL_MODE);
        armTalon.setInverted(ArmConstants.INVERTED);
        setCurrentLimit(ArmConstants.PEAK_CURRENT_LIMIT, ArmConstants.PEAK_TIME_MS,
                ArmConstants.CONTINUOUS_CURRENT_LIMIT);
    }

    /**
     * Sets the current limit on the Arm.
     * @param peakCurrentLimit the peak current limit (the initial limit, to last for the given amount of time)
     * @param peakTime the time for which the peak current limit should last 
     * @param continuousLimit the limit after peakTime milliseconds have passed
     */
    private void setCurrentLimit (int peakCurrentLimit, int peakTime, int continuousLimit) {
        armTalon.configPeakCurrentLimit(peakCurrentLimit, RobotMap.TIMEOUT);

        armTalon.configPeakCurrentDuration(peakTime, RobotMap.TIMEOUT);

        armTalon.configContinuousCurrentLimit(continuousLimit, RobotMap.TIMEOUT);

        armTalon.enableCurrentLimit(true);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    protected void initDefaultCommand () {
        setDefaultCommand (new MoveArmManual(OI.CONTROLLER_JOYSTICK_DEADBAND));
    }

    /**
     * Gets the current instance of Arm, creating a new one if necessary.
     * @return the current instance of Arm
     */
    public static Arm getInstance () {
        if (arm == null) {
            arm = new Arm();
        }
        return arm;
    }

    /**
     * Gets the arm Talon.
     * @return the Talon corresponding to the arm
     */
    public TalonSRX getArmTalon () {
        return armTalon;
    }
    
    /**
     * Sets the arm Talon to move at a given percent [-1, 1].
     * @param output the percent at which the arm Talon should be moved
     */
    public void setArmOutput (double output)
    {
        getArmTalon().set(ControlMode.PercentOutput, output);
    }
    
    /**
     * Determines whether the arm has reached the end of its motion in either direction.
     * @return true if the arm has reached the end of its motion; false otherwise
     */
    public boolean hasHitEnd () {
        return getArmTalon().getOutputCurrent() >= ArmConstants.CURRENT_SPIKE;
    }

}
