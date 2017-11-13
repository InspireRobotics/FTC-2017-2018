package org.firstinspires.ftc.team10489.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by FRC4283 on 9/27/2017.
 */

public class ThreeKiwiRobot extends Subsystem {

    public static double speed = .5;

    /**Top*/
    private DcMotor one;
    /**Right*/
    private DcMotor two;
    /**Left*/
    private DcMotor three;

    public ThreeKiwiRobot(HardwareMap hMap, Telemetry telemetry){
        super(hMap, telemetry);
    }

    public void set(double one, double two, double three){
        this.one.setPower(one);
        this.two.setPower(two);
        this.three.setPower(three);
    }

    /**
     * Stops the Subsystem
     */
    public void stop(){
        one.setPower(0);
        two.setPower(0);
        three.setPower(0);
    }

    public void init() {
        try{
            telemetry.addLine("Loading Kiwi Motors");
            one = this.loadMotor("one", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            two = this.loadMotor("two", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            three = this.loadMotor("three", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }catch(NullPointerException e){
            telemetry.addLine("Failed to init kiwi drive!");
        }
    }
}
