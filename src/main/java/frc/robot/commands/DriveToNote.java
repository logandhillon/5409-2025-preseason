package frc.robot.commands;

// 5409: The Chargers
// http://github.com/FRC5409

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.kDrive;
import frc.robot.Constants.kVision;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

/**
 * @author Logan Dhillon
 */
public class DriveToNote extends Command {
    private final Vision vision;
    private final Drivetrain drivetrain;

    public DriveToNote() {
        vision = Vision.getInstance();
        drivetrain = Drivetrain.getInstance();

        addRequirements(vision, drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrain.moveWithVelocity(() -> vision.getTargetXOffset(), () -> vision.getTargetYOffset(),
                () -> kDrive.MAX_TURN_ANGULAR_VELOCITY);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        double dx = vision.getTargetXOffset();
        double dy = vision.getTargetYOffset();
        return (dx <= kVision.LOCATOR_TOLERANCE || dx >= -kVision.LOCATOR_TOLERANCE)
                && (dy <= kVision.LOCATOR_TOLERANCE || dy >= -kVision.LOCATOR_TOLERANCE);
    }

}