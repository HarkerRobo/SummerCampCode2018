package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.DriveWithVelocityManual;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.RobotMap.CAN_IDs;
import frc.robot.RobotMap.DrivetrainConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Represents the Drivetrain on the robot.
 * 
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class Drivetrain extends Subsystem {
    private static Drivetrain dt; // singleton

    private TalonSRX leftTalonMaster;
    private TalonSRX rightTalonMaster;
    private TalonSRX leftTalonFollower;
    private TalonSRX rightTalonFollower;

    /**
     * Constructs a new Drivetrain.
     */
    public Drivetrain () {
        leftTalonMaster = new TalonSRX(CAN_IDs.DT_LEFT_TALON_MASTER);
        rightTalonMaster = new TalonSRX(CAN_IDs.DT_RIGHT_TALON_MASTER);

        leftTalonFollower = new TalonSRX(CAN_IDs.DT_LEFT_TALON_FOLLOWER);
        rightTalonFollower = new TalonSRX(CAN_IDs.DT_RIGHT_TALON_FOLLOWER);
    }

    /**
     * Calls important initialization code on the drivetrain Talons.
     */
    public void talonInit () {
        followMasters();
        invertTalons();
        setNeutralModes();
        setCurrentLimit(DrivetrainConstants.PEAK_CURRENT_LIMIT,
                DrivetrainConstants.PEAK_TIME_MS,
                DrivetrainConstants.CONTINUOUS_CURRENT_LIMIT);

    }

    /**
     * Ensures the follow talons follow the master talons.
     */
    private void followMasters () {
        leftTalonFollower.follow(leftTalonMaster);
        rightTalonFollower.follow(rightTalonMaster);
    }

    /**
     * Inverts the talons such that they turn in the correct direction.
     */
    private void invertTalons () {
        leftTalonMaster.setInverted(DrivetrainConstants.LEFT_MASTER_INVERTED);
        rightTalonMaster.setInverted(DrivetrainConstants.RIGHT_MASTER_INVERTED);
        leftTalonFollower.setInverted(DrivetrainConstants.LEFT_FOLLOWER_INVERTED);
        leftTalonFollower.setInverted(DrivetrainConstants.RIGHT_FOLLOWER_INVERTED);
    }

    /**
     * Sets the talon neutral modes (brake or coast).
     */
    private void setNeutralModes () {
        leftTalonMaster.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE);
        leftTalonFollower.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE);
        rightTalonMaster.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE);
        rightTalonFollower.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE);
    }

    /**
     * Sets the current limit on the Drivetrain.
     * @param peakCurrentLimit the peak current limit (the initial limit, to last for the given amount of time)
     * @param peakTime the time for which the peak current limit should last 
     * @param continuousLimit the limit after peakTime milliseconds have passed
     */
    private void setCurrentLimit (int peakCurrentLimit, int peakTime, int continuousLimit) {
        getLeftTalonMaster().configPeakCurrentLimit(peakCurrentLimit, RobotMap.TIMEOUT);
        getRightTalonMaster().configPeakCurrentLimit(peakCurrentLimit, RobotMap.TIMEOUT);

        getLeftTalonMaster().configPeakCurrentDuration(peakTime, RobotMap.TIMEOUT);
        getRightTalonMaster().configPeakCurrentDuration(peakTime, RobotMap.TIMEOUT);

        getLeftTalonMaster().configContinuousCurrentLimit(continuousLimit, RobotMap.TIMEOUT);
        getRightTalonMaster().configContinuousCurrentLimit(continuousLimit, RobotMap.TIMEOUT);

        getLeftTalonMaster().enableCurrentLimit(true);
        getRightTalonMaster().enableCurrentLimit(true);
    }

    /**
     * {@inheritDoc}
     */
    public void initDefaultCommand () {
        setDefaultCommand(new DriveWithVelocityManual(OI.CONTROLLER_JOYSTICK_DEADBAND));
    }

    /**
     * Gets the current instance of Drivetrain, creating a new instance if one has not yet been created.
     * @return the instance of Drivetrain
     */
    public static Drivetrain getInstance () {
        if (dt == null) {
            dt = new Drivetrain();
        }
        return dt;
    }

    /**
     * Gets the left master Talon.
     * @return the master Talon on the left side
     */
    public TalonSRX getLeftTalonMaster () {
        return leftTalonMaster;
    }

    /**
     * Gets the right master Talon.
     * @return the master Talon on the right side
     */
    public TalonSRX getRightTalonMaster () {
        return rightTalonMaster;
    }

    /**
     * Gets the left follower Talon.
     * @return the follower Talon on the left side
     */
    public TalonSRX getLeftTalonFollower () {
        return leftTalonFollower;
    }

    /**
     * Gets the right follower Talon.
     * @return the follower Talon on the right side
     */
    public TalonSRX getRightTalonFollower () {
        return rightTalonFollower;
    }

    /**
     * Drives the robot with a given percent output.
     * 
     * @param speed the speed at which the robot should drive
     * @param turn the amount by which the robot should turn (negative for left, positive for right)
     */
    public void arcadeDrivePercentOutput (double speed, double turn) {
        double divisor = Math.max(1, Math.max(Math.abs(speed + Math.pow(turn, 2)), Math.abs(speed - Math.pow(turn, 2))));
        double leftOutputBase = speed + turn * Math.abs(turn);
        leftTalonMaster.set(ControlMode.PercentOutput, leftOutputBase / divisor);

        double rightOutputBase = speed - turn * Math.abs(turn);
        rightTalonMaster.set(ControlMode.PercentOutput,
                rightOutputBase/divisor);
    }
}