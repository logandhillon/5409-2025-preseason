package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.kIndexer;

public class Indexer extends SubsystemBase {

	private static Indexer instance = null;

	private final CANSparkMax motor;
	private final DigitalInput irSensor;

	private Indexer() {
		motor = new CANSparkMax(kIndexer.MOTOR_ID, MotorType.kBrushless);
		configMotor(motor, false);

		irSensor = new DigitalInput(kIndexer.IR_SENSOR_PORT);
	}

	public static Indexer getInstance() {
		if (instance == null)
			instance = new Indexer();
		return instance;
	}

	private void configMotor(CANSparkMax motor, boolean isInverted) {
		motor.restoreFactoryDefaults();
		motor.setInverted(isInverted);
		motor.setIdleMode(IdleMode.kBrake);
		motor.setSmartCurrentLimit(kIndexer.CURRENT_LIMIT);

		motor.burnFlash();
	}

	/**
	 * @return true if IR tripped
	 */
	public boolean checkIR() {
		return !irSensor.get();
	}

	/**
	 * @param volts signed number (max 12V)
	 */
	public void setVoltage(double volts) {
		motor.setVoltage(volts);
	}
}
