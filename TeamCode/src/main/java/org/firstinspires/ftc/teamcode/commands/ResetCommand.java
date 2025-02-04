package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.commands.outtake.OuttakeIntakeCommand;

/*
resets all subsystems to how they were in initialization state
 */
public class ResetCommand extends SequentialCommandGroup {
    public ResetCommand(){
        super (
                new SequentialCommandGroup(
                        new SlidesLiftSlightlyCommand(),
                        new HorizontalSlidesRetractCommand(),
                        new WristParallelCommand(),
                        new OuttakeIntakeCommand(),
                        new WaitCommand(500),
                        new VertSlidesStartCommand()
                )
        );
    }
}
