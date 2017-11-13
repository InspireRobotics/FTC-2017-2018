package org.firstinspires
        .ftc.team10489.hardware;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by FRC4283 on 9/21/2017.
 */

public class Subsystem {

    protected HardwareMap hMap;
    protected Telemetry telemetry;

    public Subsystem(HardwareMap hMap, Telemetry telemetry){
        this.hMap = hMap;
        this.telemetry = telemetry;
    }

    protected DcMotor loadMotor(String name){
        try{
            return hMap.dcMotor.get(name);
        }catch(NullPointerException e){
            telemetry.addLine("Error: Could not Load Motor: " + name + "   " + e.getMessage());
        }
        return null;
    }

    protected DcMotor loadMotor(String name, DcMotor.RunMode rm){
        DcMotor motor = loadMotor(name);
        motor.setMode(rm);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        return motor;
    }

}
