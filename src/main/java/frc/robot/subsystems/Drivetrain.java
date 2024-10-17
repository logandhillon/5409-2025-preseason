package frc.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.kDrive;
import frc.robot.generated.TunerConstantsComp;

// 5409: The Chargers
// http://github.com/FRC5409

public class Drivetrain extends SwerveDrivetrain implements Subsystem {
    public final SwerveRequest.FieldCentric teleopDrive = new SwerveRequest.FieldCentric()
            .withDeadband(kDrive.MAX_DRIVE_VELOCIY * 0.1).withRotationalDeadband(kDrive.MAX_TURN_ANGULAR_VELOCITY * 0.1)
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

    public Drivetrain(SwerveDrivetrainConstants driveTrainConstants, SwerveModuleConstants... modules) {
        super(driveTrainConstants, modules);
    }

    private static Drivetrain instance = null;

    // Get subsystem
    public static Drivetrain getInstance() {
        if (instance == null)
            instance = TunerConstantsComp.DriveTrain;

        return instance;
    }

    public Command moveWithVelocity(Supplier<Double> vx, Supplier<Double> vy, Supplier<Double> rotRate) {
        return this.applyRequest(() -> this.teleopDrive.withVelocityX(vx.get()).withVelocityY(vy.get())
                .withRotationalRate(rotRate.get()));
    }

    public Command applyRequest(Supplier<SwerveRequest> requestSupplier) {
        return run(() -> this.setControl(requestSupplier.get()));
    }
}