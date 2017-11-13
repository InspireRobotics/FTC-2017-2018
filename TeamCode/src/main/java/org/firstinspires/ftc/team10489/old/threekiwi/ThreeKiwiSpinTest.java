package org.firstinspires.ftc.team10489.old.threekiwi;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team10489.hardware.ThreeKiwiRobot;

@TeleOp(name="Kiwi Drive 3 Wheel: Spin", group ="3Kiwi")
@Disabled
public class ThreeKiwiSpinTest extends LinearOpMode {

    private ThreeKiwiRobot robot = new ThreeKiwiRobot(hardwareMap, telemetry);

    public void runOpMode(){
        telemetry.addLine("Initializing Subsystem: 3 Kiwi (Spin Test)");
        robot.init();
        telemetry.update();

        this.waitForStart();

        while(this.opModeIsActive()){
            double turn = -this.gamepad1.right_stick_x * .5;
            robot.set(turn, turn, turn);

            telemetry.addLine("Running Subsystem: Kiwi Sucks (Turn Test)");
            telemetry.addLine("Turn: " + turn);
            telemetry.update();
        }
    }

}
