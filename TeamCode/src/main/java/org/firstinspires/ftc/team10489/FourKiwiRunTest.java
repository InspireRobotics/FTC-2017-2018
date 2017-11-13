package org.firstinspires.ftc.team10489;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU;
import org.firstinspires.ftc.team10489.hardware.FourKiwiRobot;

@TeleOp(name="Kiwi Drive 4 Wheel: Run", group ="4Kiwi")
public class FourKiwiRunTest extends LinearOpMode {

    private FourKiwiRobot robot;
    private double SPEED = .5;

    public void runOpMode(){
        telemetry.addLine("Initializing Subsystem: 4 Kiwi (Complete Test)");
        robot = new FourKiwiRobot(hardwareMap, telemetry);
        robot.init();
        telemetry.update();

        Class a = SensorBNO055IMU.class;

        this.waitForStart();

        while(this.opModeIsActive()){
            robot.set(.3, .3, .3, .3);


            telemetry.addLine("Running Subsystem: 4 Kiwi (Complete Test)");
            telemetry.update();
        }
    }

}
