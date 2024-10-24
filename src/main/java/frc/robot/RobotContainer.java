// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.kDrive;
import frc.robot.commands.DriveToNote;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
    private final Drivetrain sys_drivetrain = Drivetrain.getInstance();

    private Command cmd_teleopDrive;

    private final CommandXboxController m_driverController = new CommandXboxController(
            OperatorConstants.kDriverControllerPort);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        configureBindings();
    }

    /**
     * Controller -> command bindings
     */
    private void configureBindings() {
        // drive with joystick
        cmd_teleopDrive = sys_drivetrain.moveWithVelocity(
                () -> -m_driverController.getLeftY() * kDrive.MAX_DRIVE_VELOCIY,
                () -> -m_driverController.getLeftX() * kDrive.MAX_DRIVE_VELOCIY,
                () -> (m_driverController.getLeftTriggerAxis() - m_driverController.getRightTriggerAxis())
                        * kDrive.MAX_TURN_ANGULAR_VELOCITY);

        sys_drivetrain.setDefaultCommand(cmd_teleopDrive);

        m_driverController.y().onTrue(new DriveToNote());
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
