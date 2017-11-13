package org.firstinspires.ftc.team10489;

/**
 * Created by FRC4283 on 10/18/2017.
 */


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.system.SystemProperties;
import org.firstinspires.ftc.team10489.hardware.FourKiwiRobot;
import org.firstinspires.ftc.team10489.hardware.RevGyro;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.android.dx.rop.code.Exceptions;
import org.firstinspires.ftc.team10489.hardware.FourKiwiRobot;
import org.firstinspires.ftc.team10489.hardware.RevGyro;

@Autonomous(name="Auto #1", group ="4Kiwi")
public class MainAuto extends LinearOpMode {

    private FourKiwiRobot robot;
    private RevGyro gyro;
    private static final double DRIVE_SPEED = .5;
    private static final int THRESHOLD = 5;

    public void runOpMode(){
        telemetry.addLine("Initializing Subsystem: 4 Kiwi (Auto Test)");
        robot = new FourKiwiRobot(hardwareMap, telemetry);
        robot.init();
        gyro = new RevGyro(hardwareMap, telemetry);
        telemetry.update();
        long stopTime;

        this.waitForStart();
        stopTime = System.currentTimeMillis() + 5000;

        while(System.currentTimeMillis() < stopTime){
            double left = DRIVE_SPEED;
            double right = DRIVE_SPEED;
            double angle = gyro.getAngle();
            double error = 0 - angle;

            if(error < THRESHOLD && error < 0){
                left += .1;
            }else if(error > THRESHOLD){
                right += .1;
            }

            robot.set(-left, right, 0, 0);
        }
    }

}
