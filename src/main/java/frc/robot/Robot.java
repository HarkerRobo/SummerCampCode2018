/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the TimedRobot documentation. If you change the name of this class or the package after creating this
 * project, you must also update the build.gradle file in the project.
 */
public class Robot extends TimedRobot {
    private static Drivetrain dt;
    private static Arm arm;
    private static Intake intake;
    private static OI oi;

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    @Override 
    public void robotInit () {
        dt = Drivetrain.getInstance();
        arm = Arm.getInstance();
        intake = Intake.getInstance(); 
        oi = OI.getInstance();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for items like diagnostics that you want
     * ran during disabled, autonomous, teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow and SmartDashboard integrated
     * updating.
     */
    @Override
    public void robotPeriodic () {}

    @Override
    /**
     * This function is called when the teleoperated period begins.
     */
    public void teleopInit () {
        getDrivetrain().talonInit();
        getArm().talonInit();
        getIntake().talonInit();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic () {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic () {}

    /**
     * Gets the current instance of OI.
     * @return the current OI instance
     */
    public static OI getOI () {
        return oi;
    }

    /**
     * Gets the current instance of Drivetrain.
     * @return the current Drivetrain instance
     */
    public static Drivetrain getDrivetrain () {
        return dt;
    }

    /**
     * Gets the current instance of Arm.
     * @return the current Arm instance
     */
    public static Arm getArm () {
        return arm;
    }

    /**
     * Gets the current instance of Intake.
     * @return the current Intake instance
     */
    public static Intake getIntake () {
        return intake;
    }

}
