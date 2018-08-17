package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Drives the robot with a given percent output using manual control.
 * 
 * @author Finn Frankis
 * @version Aug 16, 2018
 */
public class DriveWithVelocityManual extends Command {
    private double deadband;

    /**
     * Constructs a new DriveWithVelocityManual.
     * @param deadband the amount below which all joystick input will be treated as noise and ignored
     */
    public DriveWithVelocityManual (double deadband) {
        requires(Robot.getDrivetrain());
        this.deadband = deadband;
    }

    /**
     * {@inheritDoc}
     */
    public void execute () {
        OI oi = OI.getInstance();

        double leftX = oi.getGamepad().getLeftX();
        double leftY = oi.getGamepad().getLeftY();

        if (Math.abs(leftX) < deadband)
            leftX = 0;
        else {
            leftX = leftX - Math.signum(leftX) * deadband;
            leftX /= 1 - deadband;
        }

        if (Math.abs(leftY) < deadband)
            leftY = 0;
        else {
            leftY -= Math.signum(leftY) * deadband;
            leftY /= 1 - deadband;
        }

        Robot.getDrivetrain().arcadeDrivePercentOutput(leftY, leftX);
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isFinished () {
        return false;
    }
}