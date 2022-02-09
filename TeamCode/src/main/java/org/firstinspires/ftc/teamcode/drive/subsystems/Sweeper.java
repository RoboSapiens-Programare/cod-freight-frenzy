package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;

public class Sweeper {
    public DcMotor motorBrat;
    public DcMotor motorSweeper;
    public Servo servoBrat;
    public Servo servoBrat2;
    public DcMotor motorCuva;

    public Sweeper(HardwareMap hardwareMap){
        motorBrat = hardwareMap.dcMotor.get("motorBrat");
        motorSweeper = hardwareMap.dcMotor.get("motorSweeper");
//        servoBrat = hardwareMap.servo.get("servoBrat");
//        servoBrat2 = hardwareMap.servo.get("servoBrat2");
        motorCuva = hardwareMap.dcMotor.get("motorCuva");


        motorBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBrat.setTargetPosition(0);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorBrat.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBrat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorCuva.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorCuva.setTargetPosition(0);
        motorCuva.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorCuva.setDirection(DcMotorSimple.Direction.FORWARD);
        motorCuva.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorSweeper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorSweeper.setDirection(DcMotorSimple.Direction.FORWARD);
        motorSweeper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        servoBrat.setPosition(0.7);
//        servoBrat2.setPosition(0.3);
    }

    public void eject() { motorSweeper.setPower(0.5); }
    public void reverseEject() { motorSweeper.setPower(-0.5); }
    public void outtake(){
        motorSweeper.setPower(0.8);
    }
    public void intake(){
        motorSweeper.setPower(-1);
    }
    public void stopSweep(){
        motorSweeper.setPower(0);
    }

    public void defaultPosition(){
        servoBrat.setPosition(0.7);
        servoBrat2.setPosition(0.3);
    }
    public void pickupPosition(){
        servoBrat.setPosition(0);
        servoBrat2.setPosition(1);
    }

    public void midPosition(){
        servoBrat.setPosition(0.3);
        servoBrat2.setPosition(0.7);
    }

    public void ridicaBrat(double speed){
        motorBrat.setPower(Math.min(speed, 1));
    }

    public void coboaraBrat(double speed){
        motorBrat.setPower(Math.max(-speed, -1));
    }

    public void stopBrat(){
//        motorBrat.setPower(0);
    }

    public void autoRidicare(){
        motorBrat.setTargetPosition(motorBrat.getCurrentPosition() + 200);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBrat.setPower(0.2);
    }

    public void autoCoborare(){
        motorBrat.setTargetPosition(motorBrat.getCurrentPosition() - 200);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBrat.setPower(-0.2);
    }

    public void ridicareCuva(){
        motorCuva.setTargetPosition(motorCuva.getCurrentPosition() + 200);
        motorCuva.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorCuva.setPower(-0.7);
    }

    public void coborareCuva(){
        motorCuva.setTargetPosition(motorCuva.getCurrentPosition() - 200);
        motorCuva.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorCuva.setPower(0.7);
    }

    public void stopCuva(){
        motorCuva.setPower(0);
    }
}