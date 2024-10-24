package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.kIndexer;
import frc.robot.Constants.kIntake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class IntakeNote extends SequentialCommandGroup {
    public IntakeNote(Intake sys_intake, Indexer sys_indexer) {
        addCommands(Commands.race(Commands.startEnd(() -> {
            sys_intake.setVoltage(kIntake.VOLTAGE);
            sys_indexer.setVoltage(kIndexer.VOLTAGE);
        }, () -> {
            sys_intake.setVoltage(0);
            sys_indexer.setVoltage(0);
        }, sys_intake, sys_indexer), Commands.waitUntil(() -> sys_indexer.checkIR())));
    }
}
