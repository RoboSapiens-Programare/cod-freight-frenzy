package org.firstinspires.ftc.teamcode.drive.localization;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.TwoTrackingWheelLocalizer;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.mechanumSamples.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.BNO055IMUUtil;
import org.firstinspires.ftc.teamcode.util.Encoder;
import org.jetbrains.annotations.NotNull;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

public class TwoWheelTrackingLocalizer extends TwoTrackingWheelLocalizer {
    public static double TICKS_PER_REV = 8192;
    public static double WHEEL_RADIUS = 0.8; // in
    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed

    public static double PARALLEL_X = 0; // X is the forward and back direction
    public static double PARALLEL_Y = 7.87; // Y is the strafe direction

    public static double PERPENDICULAR_X = 0; // X is the forward and back direction
    public static double PERPENDICULAR_Y = 0; // Y is the strafe direction

    private Encoder parallelEncoder, perpendicularEncoder;

    private BNO055IMU imu;

    public TwoWheelTrackingLocalizer(HardwareMap hardwareMap) {
        super(Arrays.asList(
                new Pose2d(PARALLEL_X, PARALLEL_Y, 0),              //leftEncoder
                new Pose2d(PERPENDICULAR_X, PERPENDICULAR_Y, 0)    //frontEncoder
        ));
        parallelEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "parallelEncoder"));
        perpendicularEncoder = new Encoder((hardwareMap.get(DcMotorEx.class, "perpendicularEncoder")));

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
    }

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    @NotNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
                encoderTicksToInches(parallelEncoder.getCurrentPosition()),
                encoderTicksToInches(perpendicularEncoder.getCurrentPosition())
        );
    }

    public List<Integer> getTicks() {
        return Arrays.asList(
                parallelEncoder.getCurrentPosition(),
                perpendicularEncoder.getCurrentPosition()
        );
    }

    public List<Double> getTicksPerREV() {
        return Arrays.asList(
                TICKS_PER_REV
        );
    }

    @Override
    public double getHeading() {
        return imu.getAngularOrientation().firstAngle;
    }


    @Override
    public List<Double> getWheelVelocities() {
        return Arrays.asList(
                encoderTicksToInches(parallelEncoder.getRawVelocity()),
                encoderTicksToInches(perpendicularEncoder.getRawVelocity())
        );
    }

    @Override
    public Double getHeadingVelocity() {
        return (double) imu.getAngularVelocity().xRotationRate;
    }

    
}