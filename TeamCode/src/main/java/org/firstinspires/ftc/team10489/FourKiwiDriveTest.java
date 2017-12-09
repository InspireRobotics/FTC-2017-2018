package org.firstinspires.ftc.team10489;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team10489.hardware.FourKiwiRobot;
import org.firstinspires.ftc.team10489.hardware.JewelPusher;
import org.firstinspires.ftc.team10489.hardware.GlyphSystem;
import org.firstinspires.ftc.team10489.hardware.RevGyro;

@TeleOp(name="Kiwi Drive 4 Wheel: Drive", group ="4Kiwi")
public class FourKiwiDriveTest extends LinearOpMode {

    private static final double LOW_SPEED = 0.4;
    private static final double MEDIUM_SPEED = 0.7;

    private FourKiwiRobot robot;
    private RevGyro gyro;
    private JewelPusher jewelPusher;
    private GlyphSystem lift;
    private enum Diagonal {NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST, NONE};
    private enum DriveMaxPower {LOW, MEDIUM, HIGH};
    private double DIAGONAL_SPEED = .5;

    public void runOpMode(){
        telemetry.addLine("Initializing Subsystem: 4 Kiwi (Complete Test)");
        robot = new FourKiwiRobot(hardwareMap, telemetry);
        robot.init();
//        gyro = new RevGyro(hardwareMap, telemetry);
        jewelPusher = new JewelPusher(hardwareMap, telemetry);
        jewelPusher.init();
        lift = new GlyphSystem(hardwareMap, telemetry);
        lift.init();
        telemetry.update();
        DriveMaxPower driveMaxPower = DriveMaxPower.HIGH;

        this.waitForStart();

        while(this.opModeIsActive()){
            double drivePower = gamepad1.right_stick_y;
            double sidePower = gamepad1.right_stick_x;
            double turnPower = gamepad1.left_stick_x;
            double liftPower = gamepad2.right_stick_y;
            float grabbingPower = (gamepad2.left_stick_y) / 2;

            Diagonal diagonal = getDiagonal();

            driveMaxPower = getDriveMaxPower(driveMaxPower);

            if(drivePower > 0.2 || drivePower < -0.2){//Drive Forward or Backward
                if(drivePower > LOW_SPEED && driveMaxPower == DriveMaxPower.LOW){
                    drivePower = LOW_SPEED;
                }else if(drivePower < -LOW_SPEED && driveMaxPower == DriveMaxPower.LOW){
                    drivePower = -LOW_SPEED;
                }else if(drivePower > MEDIUM_SPEED && driveMaxPower == DriveMaxPower.MEDIUM){
                    drivePower = MEDIUM_SPEED;
                }else if(drivePower < -MEDIUM_SPEED && driveMaxPower == DriveMaxPower.MEDIUM){
                    drivePower = -MEDIUM_SPEED;
                }


                if(drivePower > 0){
                    robot.set(-drivePower, drivePower, -drivePower, drivePower);
                }else{
                    robot.set(-drivePower, drivePower, -drivePower, drivePower);
                }
            }else if(sidePower > 0.2 || sidePower < -0.2){//Drive Left or Right
                if(sidePower > LOW_SPEED && driveMaxPower == DriveMaxPower.LOW){
                    sidePower = LOW_SPEED;
                }else if(drivePower < -LOW_SPEED && driveMaxPower == DriveMaxPower.LOW){
                    sidePower = -LOW_SPEED;
                }else if(drivePower > MEDIUM_SPEED && driveMaxPower == DriveMaxPower.MEDIUM){
                    sidePower = MEDIUM_SPEED;
                }else if(drivePower < -MEDIUM_SPEED && driveMaxPower == DriveMaxPower.MEDIUM){
                    sidePower = -MEDIUM_SPEED;
                }

               if(sidePower > 0){
                    robot.set(-sidePower, -sidePower,  sidePower, sidePower);
                }else{
                    robot.set(-sidePower, -sidePower, sidePower, sidePower);
                }
            }else if(turnPower > 0.2 || turnPower < -0.2){
                if(turnPower > LOW_SPEED && driveMaxPower == DriveMaxPower.LOW){
                    turnPower = LOW_SPEED;
                }else if(turnPower < -LOW_SPEED && driveMaxPower == DriveMaxPower.LOW){
                    turnPower = -LOW_SPEED;
                }else if(turnPower > MEDIUM_SPEED && driveMaxPower == DriveMaxPower.MEDIUM){
                    turnPower = MEDIUM_SPEED;
                }else if(turnPower < -MEDIUM_SPEED && driveMaxPower == DriveMaxPower.MEDIUM){
                    turnPower = -MEDIUM_SPEED;
                }

                robot.set(-turnPower, -turnPower, -turnPower, -turnPower);
            }else if(diagonal != Diagonal.NONE){
                if(diagonal == Diagonal.NORTHEAST){
                    robot.set(-DIAGONAL_SPEED, 0, 0, DIAGONAL_SPEED);
                }else if(diagonal == Diagonal.NORTHWEST){
                    robot.set(0, DIAGONAL_SPEED, -DIAGONAL_SPEED, 0);
                }else if(diagonal == Diagonal.SOUTHWEST){
                    robot.set(DIAGONAL_SPEED, 0, 0, -DIAGONAL_SPEED);
                }else if(diagonal == Diagonal.SOUTHEAST){
                    robot.set(0, -DIAGONAL_SPEED, DIAGONAL_SPEED, 0);
                }

            }else{
                robot.stop();
            }

            if(liftPower > 0.2 || liftPower < -0.2) {
                lift.setLiftPower(liftPower);
            }else{
                lift.setLiftPower(0.0);
            }

            if(grabbingPower > 0.1){
                lift.setGrabberPower(grabbingPower);
            }else if(grabbingPower < -0.1){
                lift.setGrabberPower(grabbingPower);
            }else{
                lift.setGrabberPower(0);
            }

//            gyro.update();

//            telemetry.addLine("Running Subsystem: 4 Kiwi (Complete Test)");
//            telemetry.addLine("Gyro: " + gyro.getAngle() + "   Raw: " + gyro.getCurr());
//            telemetry.addLine("Lift: " + lift.returnLiftPosition());
            telemetry.addLine("Color Sensor   Red: " + jewelPusher.getRed() + "  Blue: " + jewelPusher.getBlue());
//            telemetry.addLine("Lift Power: " + liftPower);
//            telemetry.addLine("Power: " + drivePower + "  " + sidePower + turnPower);
            telemetry.addLine("Max Drive Power: " + driveMaxPower);
            telemetry.addLine("Servo Position: " + jewelPusher.getServoPos());
//            telemetry.addLine("Overall: " + gyro.getOverall());
//            telemetry.addLine("Direction: " + gyro.direction);
            telemetry.update();
        }
    }

    public Diagonal getDiagonal(){
        if(gamepad1.dpad_left && gamepad1.dpad_up){
            return Diagonal.NORTHWEST;
        }else if(gamepad1.dpad_up && gamepad1.dpad_right){
            return Diagonal.NORTHEAST;
        }else if(gamepad1.dpad_right && gamepad1.dpad_down){
            return Diagonal.SOUTHEAST;
        }else if(gamepad1.dpad_left && gamepad1.dpad_down){
            return Diagonal.SOUTHWEST;
        }
        return Diagonal.NONE;
    }

    public DriveMaxPower getDriveMaxPower(DriveMaxPower curr){
        if (gamepad1.y && !gamepad1.b && !gamepad1.a){
            return DriveMaxPower.HIGH;
        }else if(!gamepad1.y && gamepad1.b && !gamepad1.a){
            return DriveMaxPower.MEDIUM;
        }else if(!gamepad1.y && !gamepad1.b && gamepad1.a){
            return DriveMaxPower.LOW;
        }
        return curr;
    }
}