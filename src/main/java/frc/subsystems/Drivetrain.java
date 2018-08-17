package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
public class Drivetrain extends Subsystem {
    private TalonSRX leftTalonMaster;
    private TalonSRX rightTalonMaster;
    private TalonSRX leftTalonFollower;
    private TalonSRC rightTalonFollower;

    public Drivetrain() {
        leftTalonMaster = new TalonSRX();
        
    }
    

    public void initDefaultCommand() {

    }
}