// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.kDrive;
import frc.robot.commands.DriveToNote;
import frc.robot.commands.IntakeNote;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Vision;

public class RobotContainer {
    private final Vision sys_vision = Vision.getInstance();
    private final Drivetrain sys_drivetrain = Drivetrain.getInstance();
    private final Intake sys_intake = Intake.getInstance();
    private final Indexer sys_indexer = Indexer.getInstance();

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

        // intake note command
        m_driverController.x().whileTrue(new IntakeNote(sys_intake, sys_indexer));

        // eject note command
        m_driverController.b().onTrue(Commands.runOnce(() -> {
            sys_intake.setVoltage(-12.0);
        }, sys_intake)).onFalse(Commands.runOnce(() -> {
            sys_intake.setVoltage(0);
        }, sys_intake));

        // drive to note
        m_driverController.y().onTrue(new DriveToNote(sys_vision, sys_drivetrain, sys_intake, sys_indexer));
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
