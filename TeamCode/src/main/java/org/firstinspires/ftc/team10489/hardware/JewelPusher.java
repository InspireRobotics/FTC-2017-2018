package org.firstinspires.ftc.team10489.hardware;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by FRC4283 on 11/4/2017.
 */

public class JewelPusher extends Subsystem {

    private ColorSensor colorSensor;

    public void init(){
        try{
            telemetry.addLine("Loading Jewel System");
            colorSensor = hMap.colorSensor.get("sensor");
        }catch(NullPointerException e){
            telemetry.addLine("Failed to init Jewel system!");
        }
    }

    public int getRed(){
        return colorSensor.red();
    }

    public int getBlue(){
        return colorSensor.blue();
    }

    public int getAlpha() {return colorSensor.alpha();}

    public JewelPusher(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
    }
}
