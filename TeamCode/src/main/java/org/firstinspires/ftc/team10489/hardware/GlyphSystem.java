package org.firstinspires.ftc.team10489.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by FRC4283 on 10/11/2017.
 * This is Colin's first failed attempt at programming things.
 * How long did it take you to notice this extra line, Noah?
 * This and above added on 10/10/17 4:48 p.m.
 */
public class GlyphSystem extends Subsystem {

    private DcMotor liftMotor;
    private DcMotor grabbingMotorLeft;
    private DcMotor grabbingMotorRight;

    public void init(){
        try{
            telemetry.addLine("Loading Lift Motor");
            liftMotor = this.loadMotor("lift", DcMotor.RunMode.RUN_USING_ENCODER);
            liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            telemetry.addLine("Loading Grabbing System");
            grabbingMotorLeft = this.loadMotor("gl", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            grabbingMotorRight = this.loadMotor("gr", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            grabbingMotorLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            grabbingMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
        }catch(NullPointerException e){
            telemetry.addLine("Failed to init lift system!");
        }
    }

    public void setLiftPower(double lift){
        this.liftMotor.setPower(lift);
    }

    public void setGrabberPower(double grab){
        this.grabbingMotorLeft.setPower(grab);
        this.grabbingMotorRight.setPower(grab);
    }

    public int returnLiftPosition(){
        return liftMotor.getCurrentPosition();
    }

    public GlyphSystem(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
    }
}
