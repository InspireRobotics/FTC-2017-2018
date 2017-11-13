package org.firstinspires.ftc.team10489.old.threekiwi;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team10489.hardware.ThreeKiwiRobot;

@TeleOp(name="Kiwi Drive 3 Wheel: Complete Drive", group ="3Kiwi")
@Disabled
public class ThreeKiwiCompleteDrive extends LinearOpMode {

    private ThreeKiwiRobot robot = new ThreeKiwiRobot(hardwareMap, telemetry);


    public void runOpMode(){
        telemetry.addLine("Initializing Subsystem: 3 Kiwi (Complete Test)");
        robot.init();
        telemetry.update();

        this.waitForStart();

        while(this.opModeIsActive()){
            double joy_x = gamepad1.left_stick_x;
            double joy_y = gamepad1.left_stick_y;
            double joy_z = 0;

            if(gamepad1.right_bumper){
                joy_z = .5;
            }else if(gamepad1.left_bumper){
                joy_z = -.5;
            }

            double c1 = (joy_x + joy_z);
            double c2 = ((-((joy_x)/2))+((Math.sqrt(3)/2)*(joy_y)) + joy_z);
            double c3 = ((-((joy_x)/2))-((Math.sqrt(3)/2)*(joy_x)) + joy_z);

            telemetry.addLine("Running Subsystem: Kiwi Sucks (Drive Test)");
            telemetry.addData("Joysticks (X, Y, Z)", "%5.2f:%5.2f:%5.2f", joy_x, joy_y, joy_z);
            telemetry.addData("Motor Power (1, 2, 3)", "%5.2f:%5.2f:%5.2f", c1, c2, c3);
            telemetry.update();
        }
    }

}
