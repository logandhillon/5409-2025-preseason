// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static class OperatorConstants {
        public static final int kDriverControllerPort = 0;
    }

    public static final class kVision {
        public static final String LIMELIGHT_NAME = "limelight";
        public static final double LOCATOR_TOLERANCE = 25.0;
    }

    public static final class kDrive {
        public static final double CURRENT_LIMIT = 150.0;

        public static final double MAX_DRIVE_VELOCIY = 4.56;
        public static final double MAX_TURN_ANGULAR_VELOCITY = 10 / 2;

        public static final double NOTE_LOCATOR_MOVEMENT_RATE = 0.5;
    }

    public static final class kIntake {
        public static final int MOTOR_ID = 0; // placeholder; set to ID of intake motor
        public static final int IR_CHANNEL = 2;
        public static final int CURRENT_LIMIT = 40;
        public static final double VOLTAGE = 7;
    }

    public static final class kIndexer {
        public static final int MOTOR_ID = 0; // placeholder; set to ID of indexer motor
        public static final int CURRENT_LIMIT = 30;
        public static final int IR_SENSOR_PORT = 0;
        public static final double VOLTAGE = 8;


    }
}
