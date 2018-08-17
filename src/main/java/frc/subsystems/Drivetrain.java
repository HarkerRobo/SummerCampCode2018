package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap.CANIDs;
import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
public class Drivetrain extends Subsystem {
    private static Drivetrain dt; //singleton

    private TalonSRX leftTalonMaster;
    private TalonSRX rightTalonMaster;
    private TalonSRX leftTalonFollower;
    private TalonSRX rightTalonFollower;

    public Drivetrain() {
        leftTalonMaster = new TalonSRX(CANIDs.LEFT_TALON_MASTER);
        rightTalonMaster = new TalonSRX(CANIDs.RIGHT_TALON_MASTER);

        leftTalonFollower = new TalonSRX(CANIDs.LEFT_TALON_FOLLOWER);
        rightTalonFollower = new TalonSRX(CANIDs.RIGHT_TALON_FOLLOWER);
    }
    
    public void talonInit() {
        leftTalonFollower.follow(leftTalonMaster);
        rightTalonFollower.follow(rightTalonMaster);
    }
    public void initDefaultCommand() {

    }

    public static Drivetrain getInstance() {
        if (dt == null) {dt = new Drivetrain();}
        return dt;
    }
    public TalonSRX getLeftTalonMaster() {return leftTalonMaster;}
    public TalonSRX getRightTalonMaster() {return rightTalonMaster;}
    public TalonSRX getLeftTalonFollower() {return leftTalonFollower;}
    public TalonSRX getRightTalonFollower() {return rightTalonFollower;}
}