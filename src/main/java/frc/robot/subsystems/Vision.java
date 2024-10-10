package frc.robot.subsystems;

// 5409: The Chargers
// http://github.com/FRC5409

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;

public class Vision extends SubsystemBase {
    private static Vision instance = null;
    private String limelightName = Constants.Vision.LIMELIGHT_NAME;

    private Vision() {
    }

    public static Vision getInstance() {
        if (instance == null)
            instance = new Vision();
        return instance;
    }

    public boolean isTargetValid() {
        return LimelightHelpers.getTV(limelightName);
    }

    public double getTargetXOffset() {
        return LimelightHelpers.getTX(limelightName);
    }

    public double getTargetYOffset() {
        return LimelightHelpers.getTY(limelightName);
    }

    public double getTargetArea() {
        return LimelightHelpers.getTA(limelightName);
    }

    public double getTargetLatency() {
        return LimelightHelpers.getLatency_Pipeline(limelightName);
    }
}