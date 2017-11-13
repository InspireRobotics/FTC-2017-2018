package org.firstinspires.ftc.team10489.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by FRC4283 on 9/27/2017.
 */

public class FourKiwiRobot extends Subsystem {

    private DcMotor topLeft;
    private DcMotor topRight;
    private DcMotor bottomLeft;
    private DcMotor bottomRight;

    public FourKiwiRobot(HardwareMap hMap, Telemetry telemetry){
        super(hMap, telemetry);
    }

    public void set(double tl, double tr, double bl, double br){
        this.topLeft.setPower(tl);
        this.topRight.setPower(tr);
        this.bottomLeft.setPower(bl);
        this.bottomRight.setPower(br);
    }

    /**
     * Stops the Subsystem
     */
    public void stop(){
        topRight.setPower(0);
        topLeft.setPower(0);
        bottomLeft.setPower(0);
        bottomRight.setPower(0);
    }

    /**
     * Inits the robot
     */
    public void init() {
        try{
            telemetry.addLine("Loading Kiwi Motors");
            topLeft = this.loadMotor("tl", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            topRight = this.loadMotor("tr", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bottomLeft = this.loadMotor("bl", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bottomRight = this.loadMotor("br", DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }catch(NullPointerException e){
            telemetry.addLine("Failed to init kiwi drive!");
        }
    }
}
