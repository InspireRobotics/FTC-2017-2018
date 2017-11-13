package org.firstinspires.ftc.team10489;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.android.dx.rop.code.Exceptions;
import org.firstinspires.ftc.team10489.hardware.FourKiwiRobot;
import org.firstinspires.ftc.team10489.hardware.JewelPusher;
import org.firstinspires.ftc.team10489.hardware.Lift;
import org.firstinspires.ftc.team10489.hardware.RevGyro;

@TeleOp(name="Kiwi Drive 4 Wheel: Drive", group ="4Kiwi")
public class FourKiwiDriveTest extends LinearOpMode {

    private static final double LOW_SPEED = 0.6;
    private static final double MEDIUM_SPEED = 0.8;

    private FourKiwiRobot robot;
    private RevGyro gyro;
    private JewelPusher jewelPusher;
    private Lift lift;
    private enum Diagonal {NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST, NONE};
    private enum DriveMaxPower {LOW, MEDIUM, HIGH};
    private double DIAGONAL_SPEED = .5;

    public void runOpMode(){
        telemetry.addLine("Initializing Subsystem: 4 Kiwi (Complete Test)");
        robot = new FourKiwiRobot(hardwareMap, telemetry);
        robot.init();
        gyro = new RevGyro(hardwareMap, telemetry);
        jewelPusher = new JewelPusher(hardwareMap, telemetry);
        jewelPusher.init();
        lift = new Lift(hardwareMap, telemetry);
        lift.init();
        telemetry.update();

        this.waitForStart();

        while(this.opModeIsActive()){

            double drivePower = -gamepad1.right_stick_y;
            double sidePower = gamepad1.right_stick_x;
            double turnPower = gamepad1.left_stick_x;
            double liftPower = -gamepad2.right_stick_y;
            boolean liftPowerUp = gamepad2.y;
            boolean liftPowerDown = gamepad2.a;

            Diagonal diagonal = getDiagonal();

            DriveMaxPower driveMaxPower = getDriveMaxPower();

            if(driveMaxPower == DriveMaxPower.LOW){
                drivePower = LOW_SPEED;
                turnPower = LOW_SPEED;
                sidePower = LOW_SPEED;
            }else if(driveMaxPower == DriveMaxPower.MEDIUM){
                drivePower = MEDIUM_SPEED;
                turnPower = MEDIUM_SPEED;
                sidePower = MEDIUM_SPEED;
            }

            if(drivePower > 0.2 || drivePower < -0.2){//Drive Forward or Backward
                if(drivePower > 0){
                    robot.set(-drivePower, drivePower, -drivePower, drivePower);
                }else{
                    robot.set(-drivePower, drivePower, -drivePower, drivePower);
                }
            }else if(sidePower > 0.2 || sidePower < -0.2){//Drive Left or Right
               if(sidePower > 0){
                    robot.set(-sidePower, -sidePower, sidePower, sidePower);
                }else{
                    robot.set(-sidePower, -sidePower, sidePower, sidePower);
                }
            }else if(turnPower > 0.2 || turnPower < -0.2){
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

            if(liftPower > 0.2 || liftPower < -0.2){
                lift.set(liftPower);
            }

            if (liftPowerUp == true ^ liftPowerDown == true){
                if (liftPowerUp == true){
                    lift.set(1);
                }else{
                    lift.set(-1);
                }
            }
            gyro.update();

            telemetry.addLine("Running Subsystem: 4 Kiwi (Complete Test)");
            telemetry.addLine("Gyro: " + gyro.getAngle() + "   Raw: " + gyro.getCurr());
            telemetry.addLine("Color Sensor   Red: " + jewelPusher.getRed() + "  Blue: " + jewelPusher.getBlue());
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

    public DriveMaxPower getDriveMaxPower(){
        if (gamepad1.y && !gamepad1.b && !gamepad1.a){
            return DriveMaxPower.LOW;
        }else if(!gamepad1.y && gamepad1.b && !gamepad1.a){
            return DriveMaxPower.MEDIUM;
        }else if(!gamepad1.y && !gamepad1.b && gamepad1.a){
            return DriveMaxPower.HIGH;
        }
        return DriveMaxPower.HIGH;
    }
}
