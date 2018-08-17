package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class DriveWithVelocityManual extends Command {
    private double deadband;

    public DriveWithVelocityManual (double deadband) {
        requires(Robot.getDrivetrain());
        this.deadband = deadband;
    }

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

    protected boolean isFinished () {
        return false;
    }
}