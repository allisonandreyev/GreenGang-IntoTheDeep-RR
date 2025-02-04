package org.firstinspires.ftc.teamcode.commands.bucket;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;

/*
- opens claw (deposits sample)
- returns robot to init state
 */
public class DropDepositCommand extends SequentialCommandGroup {
    public DropDepositCommand(){
        super(
                new SequentialCommandGroup(
                        new OuttakeClawOpenCommand(),
                        new WaitCommand(2000), // less?
                        new ResetCommand()
                )
        );
    }
}
