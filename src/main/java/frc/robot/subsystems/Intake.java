package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.kIntake;

public class Intake extends SubsystemBase {

	private static Intake instance = null;

	private final CANSparkMax motor;
	private final DigitalInput irSensor;

	private Intake() {
		motor = new CANSparkMax(kIntake.MOTOR_ID, MotorType.kBrushless);
		configMotor(motor, false);

		irSensor = new DigitalInput(kIntake.IR_CHANNEL);
	}

	public static Intake getInstance() {
		if (instance == null)
			instance = new Intake();
		return instance;
	}

	private void configMotor(CANSparkMax motor, boolean isInverted) {
		motor.restoreFactoryDefaults();
		motor.setInverted(isInverted);
		motor.setIdleMode(IdleMode.kBrake);
		motor.setSmartCurrentLimit(kIntake.CURRENT_LIMIT);

		motor.burnFlash();
	}

	/**
	 * @param volts signed number (max 12V)
	 */
	public void setVoltage(double volts) {
		motor.setVoltage(volts);
	}

	/**
	 * @return true if IR tripped
	 */
	public boolean checkIR() {
		return !irSensor.get();
	}
}
