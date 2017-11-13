package org.firstinspires.ftc.team10489.old.threekiwi;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team10489.hardware.ThreeKiwiRobot;

@TeleOp(name="Kiwi Drive 3 Wheel: Drive", group ="3Kiwi")
@Disabled
public class ThreeKiwiDriveTest extends LinearOpMode {

    private ThreeKiwiRobot robot = new ThreeKiwiRobot(hardwareMap, telemetry);
    private enum Direction {NORTH, SOUTH, EAST, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST, NONE};


    public void runOpMode(){
        telemetry.addLine("Initializing Subsystem: 3 Kiwi (Drive Test)");
        robot.init();
        telemetry.update();

        this.waitForStart();

        while(this.opModeIsActive()){
            Direction dir = getDirection();
            robot.stop();

            double speed = .5;

            if(dir == Direction.NORTH){
                robot.set(0, speed, speed);
            }else if(dir == Direction.SOUTH){
                robot.set(0, -speed, -speed);
            }

            telemetry.addLine("Running Subsystem: Kiwi Sucks (Drive Test)");
            telemetry.update();
        }
    }

    public Direction getDirection(){
        if(gamepad1.dpad_up && !(gamepad1.dpad_left && gamepad1.dpad_right)){
            return Direction.NORTH;
        }else if(gamepad1.dpad_down && !(gamepad1.dpad_left && gamepad1.dpad_right)){
            return Direction.SOUTH;
        }

        return Direction.NONE;
    }

}
