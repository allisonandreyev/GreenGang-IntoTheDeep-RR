package org.firstinspires.ftc.teamcode.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;

/*
- Slides lowered
- Claw arm outwards
- Claw wrist outwards
- Opens claw
- lifts slides
 */
public class AutoSpecIntake extends SequentialCommandGroup {
    public AutoSpecIntake() {
        super (
                new SequentialCommandGroup(
                        new VertSlidesStartCommand(),
                        new ClawArmBucketCommand(),
                        new ClawWristBucketCommand(),
                        new OuttakeClawOpenCommand(),
                        new WaitCommand(1000),
                        new OuttakeClawCloseCommand(),
                        new WaitCommand(1000),
                        new SlidesLiftSlightlyCommand()
                )
        );
    }

}
