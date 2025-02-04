package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class GreenLinearOpMode extends LinearOpMode {



    @Override
    public final void runOpMode() throws InterruptedException {
        CommandScheduler.getInstance().cancelAll();

        initialize();

        while(opModeInInit()) {


            CommandScheduler.getInstance().run();

        }


        waitForStart();
        onStart();

        while (opModeIsActive()) {

            // safety for switching controllers
            if(!(gamepad1.start || gamepad2.start)) {
                periodic();
            }

            CommandScheduler.getInstance().run();

            telemetry();
        }

        end();

    }

    public void enableFTCDashboard() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    // methods to be over written
    public void initialize() {}
    public void initLoop() {}
    public void onStart() {}
    public void periodic() {}
    public void telemetry() {}
    public void end() {}

}
