package org.firstinspires.ftc.team10489.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by FRC4283 on 10/11/2017.
 * This is Colin's first failed attempt at programming things
 * How long did it take you to notice this extra line, Noah?
 * This and above added on 10/10/17 4:48 p.m.
 */
public class Lift extends Subsystem {

    private DcMotor liftMotor;
//    private Servo pivotServo;

    public void init(){
        try{
            telemetry.addLine("Loading Lift Motor");
            liftMotor = this.loadMotor("lift", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            telemetry.addLine("Loading Pivot Servo");
//            pivotServo = hMap.get(Servo.class, "pivot servo");
        }catch(NullPointerException e){
            telemetry.addLine("Failed to init lift system!");
        }
    }

    public void set(double lift){
        this.liftMotor.setPower(lift);
    }

    public Lift(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
    }
}
