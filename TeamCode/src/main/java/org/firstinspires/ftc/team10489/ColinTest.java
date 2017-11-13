package org.firstinspires.ftc.team10489;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team10489.hardware.Subsystem;

@TeleOp(name="Colin Test", group ="Tests")
public class ColinTest extends LinearOpMode {

    private Subsystem robot = new Subsystem(hardwareMap, telemetry);


    public void runOpMode(){
        telemetry.addLine("It's 4:20");
        telemetry.addLine("Dab on the haters");
        telemetry.addLine("He stacc m8");
        telemetry.addLine("BOIIIIIIIIIIIII");
        telemetry.update();
    }

}
