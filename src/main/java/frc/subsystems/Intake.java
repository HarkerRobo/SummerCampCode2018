package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.MoveIntakeManual;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.RobotMap.CAN_IDs;
import frc.robot.RobotMap.IntakeConstants;

/**
 * Represents the intake on the robot.
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class Intake extends Subsystem {
    private static Intake intake;

    private TalonSRX leftTalon;
    private TalonSRX rightTalon;

    /**
     * Represents the direction to which the robot's intake should turn (where intaking corresponds to + output and
     * outtaking corresponds to - output).
     * 
     * @author Finn Frankis
     * @version Aug 16, 2018
     */
    public enum IntakeDirection {
        INTAKE, OUTTAKE
    }

    /**
     * Constructs a new Intake.
     */
    public Intake () {
        leftTalon = new TalonSRX(CAN_IDs.INTAKE_LEFT_TALON);
        rightTalon = new TalonSRX(CAN_IDs.INTAKE_RIGHT_TALON);
    }

    /**
     * Calls all important initialization code for the intake talons.
     */
    public void talonInit () {
        setNeutralModes();
        setInverted();
        setCurrentLimit(IntakeConstants.PEAK_CURRENT_LIMIT, IntakeConstants.PEAK_TIME_MS,
                IntakeConstants.CONTINUOUS_CURRENT_LIMIT);
    }

    /**
     * Sets the netural modes for the intake talons (coast/brake).
     */
    private void setNeutralModes () {
        getLeftTalon().setNeutralMode(IntakeConstants.TALON_NEUTRAL_MODE);
        getRightTalon().setNeutralMode(IntakeConstants.TALON_NEUTRAL_MODE);
    }

    /**
     * Sets the intake talons to their correct inverted state.
     */
    private void setInverted () {
        getLeftTalon().setInverted(IntakeConstants.LEFT_INVERTED);
        getRightTalon().setInverted(IntakeConstants.RIGHT_INVERTED);
    }

    /**
     * Sets the current limit on the Intake.
     * @param peakCurrentLimit the peak current limit (the initial limit, to last for the given amount of time)
     * @param peakTime the time for which the peak current limit should last 
     * @param continuousLimit the limit after peakTime milliseconds have passed
     */
    private void setCurrentLimit (int peakCurrentLimit, int peakTime, int continuousLimit) {
        getLeftTalon().configPeakCurrentLimit(peakCurrentLimit, RobotMap.TIMEOUT);
        getRightTalon().configPeakCurrentLimit(peakCurrentLimit, RobotMap.TIMEOUT);

        getLeftTalon().configPeakCurrentDuration(peakTime, RobotMap.TIMEOUT);
        getRightTalon().configPeakCurrentLimit(peakCurrentLimit, RobotMap.TIMEOUT);

        getLeftTalon().configContinuousCurrentLimit(continuousLimit, RobotMap.TIMEOUT);
        getRightTalon().configPeakCurrentLimit(peakCurrentLimit, RobotMap.TIMEOUT);

        getLeftTalon().enableCurrentLimit(true);
        getRightTalon().configPeakCurrentLimit(peakCurrentLimit, RobotMap.TIMEOUT);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    protected void initDefaultCommand () {
        setDefaultCommand (new MoveIntakeManual(OI.CONTROLLER_TRIGGER_DEADBAND));
    }

    /**
     * Gets the leftTalon.
     * @return the leftTalon
     */
    private TalonSRX getLeftTalon () {
        return leftTalon;
    }

    /**
     * Gets the rightTalon.
     * @return the rightTalon
     */
    private TalonSRX getRightTalon () {
        return rightTalon;
    }

    /**
     * Gets the current instance of intake, creating a new one if necessary.
     * @return the current Intake instance
     */
    public static Intake getInstance () {
        if (intake == null)
            intake = new Intake();
        return intake;
    }

    /**
     * Sets the intake to turn at a given magnitude in a given direction.
     * @param direction the direction for the intake to turn
     * @param magnitude the magnitude at which the intake should turn
     */
    public void setIntakePercent (IntakeDirection direction, double magnitude) {
        setBoth(ControlMode.PercentOutput,
                (direction == IntakeDirection.INTAKE ? IntakeConstants.INTAKE_SIGN : -IntakeConstants.INTAKE_SIGN)
                        * magnitude);
    }
    
    /**
     * Sets the intake to turn at a given percent [-1, 1].
     * @param percent the percent at which the intake should be turned, where a postive percent corresponds to intaking
     */
    public void setIntakePercent (double percent) {
        setBoth (ControlMode.PercentOutput, percent);
    }

    /**
     * Sets both talons to turn a given amount.
     * @param cm the mode at which both should be set
     * @param magnitude the magnitude at which both Talons should be set
     */
    public void setBoth (ControlMode cm, double magnitude) {
        leftTalon.set(cm, magnitude);
        rightTalon.set(cm, magnitude);
    }

}
