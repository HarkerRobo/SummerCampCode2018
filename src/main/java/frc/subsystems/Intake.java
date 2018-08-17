package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.RobotMap.CAN_IDs;
import frc.robot.RobotMap.IntakeConstants;

/**
 * 
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

    public Intake () {
        setLeftTalon(new TalonSRX(CAN_IDs.INTAKE_LEFT_TALON));
        setRightTalon(new TalonSRX(CAN_IDs.INTAKE_RIGHT_TALON));
    }

    public void talonInit () {
        setNeutralModes();
        setInverted();
        setCurrentLimit(IntakeConstants.PEAK_CURRENT_LIMIT, IntakeConstants.PEAK_TIME_MS,
                IntakeConstants.CONTINUOUS_CURRENT_LIMIT);
    }

    private void setNeutralModes () {
        getLeftTalon().setNeutralMode(IntakeConstants.TALON_NEUTRAL_MODE);
        getRightTalon().setNeutralMode(IntakeConstants.TALON_NEUTRAL_MODE);
    }

    private void setInverted () {
        getLeftTalon().setInverted(IntakeConstants.LEFT_INVERTED);
        getRightTalon().setInverted(IntakeConstants.RIGHT_INVERTED);
    }

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
    * 
    */
    @Override
    protected void initDefaultCommand () {
        // TODO Auto-generated method stub

    }

    /**
     * Gets the leftTalon.
     * @return the leftTalon
     */
    private TalonSRX getLeftTalon () {
        return leftTalon;
    }

    /**
     * Sets leftTalon to a given value.
     * @param leftTalon the leftTalon to set
     *
     * @postcondition the leftTalon has been changed to the newly passed in leftTalon
     */
    private void setLeftTalon (TalonSRX leftTalon) {
        this.leftTalon = leftTalon;
    }

    /**
     * Gets the rightTalon.
     * @return the rightTalon
     */
    private TalonSRX getRightTalon () {
        return rightTalon;
    }

    /**
     * Sets rightTalon to a given value.
     * @param rightTalon the rightTalon to set
     *
     * @postcondition the rightTalon has been changed to the newly passed in rightTalon
     */
    private void setRightTalon (TalonSRX rightTalon) {
        this.rightTalon = rightTalon;
    }

    public static Intake getInstance () {
        if (intake == null)
            intake = new Intake();
        return intake;
    }

    public void setIntakePercent (IntakeDirection direction, double magnitude) {
        setBoth(ControlMode.PercentOutput,
                (direction == IntakeDirection.INTAKE ? IntakeConstants.INTAKE_SIGN : -IntakeConstants.INTAKE_SIGN)
                        * magnitude);
    }

    public void setBoth (ControlMode cm, double magnitude) {
        leftTalon.set(cm, magnitude);
        rightTalon.set(cm, magnitude);
    }

}
