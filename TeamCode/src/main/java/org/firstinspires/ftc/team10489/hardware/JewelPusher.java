package org.firstinspires.ftc.team10489.hardware;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by FRC4283 on 11/4/2017.
 */

public class JewelPusher extends Subsystem {

    private ColorSensor colorSensor;
    private Servo servo;

    public void init(){
        try{
            telemetry.addLine("Loading Jewel System");
//            servo = hMap.servo.get("servo");
//            colorSensor = hMap.colorSensor.get("color");
//            colorSensor.setI2cAddress(new I2cAddr(0x3e));
        }catch(IllegalArgumentException e){
            telemetry.addLine("Failed to init Jewel System");
            telemetry.addLine("Threw IllegalArgument Exception");
        }
        catch(NullPointerException e){
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

    public double getServoPos(){
        return servo.getPosition();
    }

    public JewelPusher(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
    }
}
