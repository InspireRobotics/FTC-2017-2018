package org.firstinspires.ftc.team10489.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

/**
 * Created by FRC4283 on 10/6/2017.
 */

public class RevGyro {

    private enum Direction {CounterClockwise, Clockwise, NONE};
    private BNO055IMU gyro;
    public Direction direction = Direction.Clockwise;//Default to clockwise
    private HardwareMap hMap;
    private Telemetry telemetry;
    private double prev = 0;
    private double curr = 0;
    private double overall = 0;

    public RevGyro(HardwareMap hMap, Telemetry telemetry){
        try{
            this.hMap = hMap;
            this.telemetry = telemetry;
            this.gyro = hMap.get(BNO055IMU.class, "imu");
            this.gyro.initialize(getParameters());
            telemetry.addLine("Initialized Rev BNU Gyro");
        }catch(NullPointerException e){
            telemetry.addLine("Failed to init Rev BNU Gyro! (Null Pointer Exception)");
        }
    }

    public void update(){
        curr = getWrappedAngle();
        boolean wrap = false;

        if((Math.signum(curr) != Math.signum(prev))&& (Math.abs(curr) > 90) && (Math.abs(prev) > 90)){//We have wrapped
            wrap(curr, prev);
            wrap = true;
        }

        if(Math.abs(curr - prev) > 3){
            if(curr > prev && !wrap){
                direction = Direction.Clockwise;
            }else if(curr < prev && wrap){
                direction = Direction.Clockwise;
            }else if(curr < prev){
                direction = Direction.CounterClockwise;
            }
        }
        prev = curr;
    }

    private void wrap(double curr, double prev){
        if (curr < prev){
            overall += 180;
        }else{
            overall -= 180;
        }
    }

    public double getAngle(){
        if(overall <= 0 && direction == Direction.CounterClockwise){//Going CounterClock and on left side of circle
            return overall + (curr - (180* (Math.abs(overall / 180))));
        }else if(overall > 0 && direction == Direction.CounterClockwise){//Going CounterClock and on right side of circle
            return overall + (curr - (180 * (-Math.abs(overall / 180))));
        }else if(overall <= 0 && direction == Direction.Clockwise){//Going Clockwise and on left side of circle
            return overall - (-curr - (180 * (  Math.abs(overall / 180))));
        }else{ //Going Clock and on right side of circle
            return overall - (-curr - (180 * (Math.abs(overall / 180))));
//            return overall - (curr - (180* (Math.abs(overall / 180))));
        }
    }

    public double getCurr(){return curr;}

    public double getOverall(){
        return overall;
    }

    public double getWrappedAngle(){
        Orientation o = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        return (double) - o.thirdAngle;
    }

    private BNO055IMU.Parameters getParameters(){
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        return parameters;
    }
}
