package org.firstinspires.ftc.team10489;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team10489.hardware.GlyphSystem;

@TeleOp(name="GlyphSystem: Run", group ="10489")
public class LiftRunTest extends LinearOpMode {

    private GlyphSystem lift;
    private double SPEED = .5;

    public void runOpMode(){
        telemetry.addLine("Initializing Subsystem: 4 Kiwi (Complete Test)");
        lift = new GlyphSystem(hardwareMap, telemetry);
        lift.init();
        telemetry.update();

        this.waitForStart();

        while(this.opModeIsActive()){
            lift.setLiftPower(gamepad1.left_stick_y);
        }
    }

}
