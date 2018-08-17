package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.DriveWithVelocityManual;
import frc.robot.RobotMap;
import frc.robot.RobotMap.CANIDs;
import frc.robot.RobotMap.DrivetrainConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drivetrain extends Subsystem {
    private static Drivetrain dt; // singleton

    private TalonSRX leftTalonMaster;
    private TalonSRX rightTalonMaster;
    private TalonSRX leftTalonFollower;
    private TalonSRX rightTalonFollower;

    public Drivetrain () {
        leftTalonMaster = new TalonSRX(CANIDs.LEFT_TALON_MASTER);
        rightTalonMaster = new TalonSRX(CANIDs.RIGHT_TALON_MASTER);

        leftTalonFollower = new TalonSRX(CANIDs.LEFT_TALON_FOLLOWER); 
        rightTalonFollower = new TalonSRX(CANIDs.RIGHT_TALON_FOLLOWER);
    }

    public void talonInit () {
        followMasters();
        invertTalons();
        setNeutralModes();
        setCurrentLimit(DrivetrainConstants.PEAK_CURRENT_LIMIT,
                DrivetrainConstants.PEAK_TIME_MS,
                DrivetrainConstants.CONTINUOUS_CURRENT_LIMIT);

    }

    private void followMasters () {
        leftTalonFollower.follow(leftTalonMaster);
        rightTalonFollower.follow(rightTalonMaster);
    }

    private void invertTalons () {
        leftTalonMaster.setInverted(DrivetrainConstants.LEFT_MASTER_INVERTED);
        rightTalonMaster.setInverted(DrivetrainConstants.RIGHT_MASTER_INVERTED);
        leftTalonFollower.setInverted(DrivetrainConstants.LEFT_FOLLOWER_INVERTED);
        leftTalonFollower.setInverted(DrivetrainConstants.RIGHT_FOLLOWER_INVERTED);
    }

    private void setNeutralModes () {
        leftTalonMaster.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE);
        leftTalonFollower.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE);
        rightTalonMaster.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE);
        rightTalonFollower.setNeutralMode(DrivetrainConstants.TALON_NEUTRAL_MODE);
    }

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

    public void initDefaultCommand () {
        setDefaultCommand(new DriveWithVelocityManual());
    }

    public static Drivetrain getInstance () {
        if (dt == null) {
            dt = new Drivetrain();
        }
        return dt;
    }

    public TalonSRX getLeftTalonMaster () {
        return leftTalonMaster;
    }

    public TalonSRX getRightTalonMaster () {
        return rightTalonMaster;
    }

    public TalonSRX getLeftTalonFollower () {
        return leftTalonFollower;
    }

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
        leftTalonMaster.set(ControlMode.PercentOutput, speed + turn);
        rightTalonMaster.set(ControlMode.PercentOutput, speed - turn);
    }
}