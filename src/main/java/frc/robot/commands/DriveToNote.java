package frc.robot.commands;

// 5409: The Chargers
// http://github.com/FRC5409

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.kDrive;
import frc.robot.Constants.kVision;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Vision;

/**
 * @author Logan Dhillon
 */
public class DriveToNote extends Command {
    private final Vision sys_vision;
    private final Drivetrain sys_drivetrain;
    private final Intake sys_intake;
    private final Indexer sys_indexer;

    private final Command cmd_intake;

    public DriveToNote(Vision vision, Drivetrain drivetrain, Intake intake, Indexer indexer) {
        sys_vision = vision;
        sys_drivetrain = drivetrain;
        sys_intake = intake;
        sys_indexer = indexer;

        cmd_intake = new IntakeNote(sys_intake, sys_indexer);

        addRequirements(sys_vision, sys_drivetrain, sys_intake, sys_indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        sys_drivetrain.moveWithVelocity(() -> sys_vision.getTargetXOffset(), () -> sys_vision.getTargetYOffset(),
                () -> kDrive.MAX_TURN_ANGULAR_VELOCITY);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        cmd_intake.schedule();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        double dx = sys_vision.getTargetXOffset();
        double dy = sys_vision.getTargetYOffset();
        return (dx <= kVision.LOCATOR_TOLERANCE || dx >= -kVision.LOCATOR_TOLERANCE)
                && (dy <= kVision.LOCATOR_TOLERANCE || dy >= -kVision.LOCATOR_TOLERANCE);
    }

}