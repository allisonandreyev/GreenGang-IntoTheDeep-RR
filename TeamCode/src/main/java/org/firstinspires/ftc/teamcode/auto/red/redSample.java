package org.firstinspires.ftc.teamcode.auto.red;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "red sample auto", group = "paths")

public class redSample extends GreenLinearOpMode {
    TrajectorySequence closeRed;

    public void initialize() {

        addDrivetrain();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-24, -64, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence closeRed = drive.trajectorySequenceBuilder(startPose)

                // PRELOAD

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ScoringHighBucketCommand().schedule();
                })
                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new OuttakeClawOpenCommand().schedule();
                    new WaitCommand(2000).schedule(); // to be changed?
                })
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ResetCommand().schedule();
                })

                // SAMPLE 1
                .splineToLinearHeading(new Pose2d(-47, -45, Math.toRadians(-90)), Math.toRadians(55))
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ScoringHighBucketCommand().schedule();
                })
                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new OuttakeClawOpenCommand().schedule();
                    new WaitCommand(2000).schedule(); // to be changed?
                })
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ResetCommand().schedule();
                })

                // SAMPLE 2
                .splineToLinearHeading(new Pose2d(-58, -45, Math.toRadians(-90)), Math.toRadians(0))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ScoringHighBucketCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new OuttakeClawOpenCommand().schedule();
                    new WaitCommand(2000).schedule(); // to be changed?
                })
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ResetCommand().schedule();
                })

                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(-56, -42, Math.toRadians(-45)), Math.toRadians(0))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ScoringHighBucketCommand().schedule();
                })
                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new OuttakeClawOpenCommand().schedule();
                    new WaitCommand(2000).schedule(); // to be changed?
                })
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ResetCommand().schedule();
                })

                // PARK
                .splineToLinearHeading(new Pose2d(-48, -58, Math.toRadians(-90)), Math.toRadians(-135))
                .build();

    }

    public void periodic() {
        drivetrain.followTrajectorySequenceAsync(closeRed);
        CommandScheduler.getInstance().run();
        drivetrain.updateTrajectory();

        Pose2d poseEstimate = drivetrain.getPoseEstimate();
        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.update();
    }
}
