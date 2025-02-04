package org.firstinspires.ftc.teamcode.subsystems.slides;

//import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

@Config

public class VertSlides implements GreenSubsystem, Subsystem {
    public DcMotor motorLeft, motorRight;
    private final PIDController pid;
    public double targetHeight;
    public static double vsP = 0.007, vsI = 0.01, vsD = 0.0001;
    private double motorPower;

    public static int
            init = 0,
            highBucket = 2890,
            lowBucket = 1636,
            highSpec = 1750,
            lowSpec = 1200;

    public enum STATE {
        INIT,
        LOW,
        START,
        HIGH,
        HIGHSPEC,
        LOWSPEC,
        MANUAL;
    }

    public STATE state;
    public enum TYPE {
        PID,
        IDLE;
    }

    public TYPE type;

    public void init() {
        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeft.setPower(0); // check what power it should be
        motorLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setPower(0); // check what power it should be
        motorRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pidTo(0);
        state = STATE.INIT;
        type = TYPE.IDLE;
    }

    public VertSlides(HardwareMap hardwareMap) {
        motorLeft = hardwareMap.get(DcMotor.class, "slidesMotorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "slidesMotorRight");
        pid = new PIDController(vsP, vsI, vsD);
        state = STATE.INIT;
        targetHeight = 0;
        motorPower = 0;
    }

    public void update() {
        switch (type) {
            case PID:
                double vsCurrPosLeft = this.getVScurrRightPos();
                motorPower = Range.clip(pid.calculate(vsCurrPosLeft), -0.6, 0.75);
                setPow(motorPower);
                break;
            case IDLE:
                motorLeft.setPower(0);
                motorRight.setPower(0);
                break;
        }

    }

    public void stopVSrotate() {
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeft.setPower(0);

        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setPower(0);
    }

    public double getVScurrRightPos() {
        return motorRight.getCurrentPosition();
    }
    public void pidTo(double targetPos) {
        type = TYPE.PID;
        pid.setSetPoint(targetPos);
    }

    public void setPow(double power) {
        motorPower = power;
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }
    public void updatePID() {
        pid.setPID(vsP, vsI, vsD);
    }

    public void manual(double num){
        state = STATE.MANUAL;
        setPow(num);
    }

    public void start(){
        state = STATE.START;
        targetHeight = 0;
        pidTo(targetHeight);
    }

    public void slightLift(){
        targetHeight = 250;
        pidTo(targetHeight);
    }

    public void highBucket(){
        state = STATE.HIGH;
        targetHeight = highBucket;
        pidTo(targetHeight);
    }
    public void highSpec(){
        state = STATE.HIGHSPEC;
        targetHeight = highSpec;
        pidTo(targetHeight);
    }

    public void lowBucket() {
        state = STATE.LOW;
        targetHeight = lowBucket;
        pidTo(targetHeight);
    }
    public void lowSpec() {
        state = STATE.LOWSPEC;
        targetHeight = lowSpec;
        pidTo(targetHeight);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("vert slides pos ", getVScurrRightPos());
        telemetry.addData("vert slides state ", state);
        telemetry.addData("vert slides curr power ", motorPower);
    }
}
