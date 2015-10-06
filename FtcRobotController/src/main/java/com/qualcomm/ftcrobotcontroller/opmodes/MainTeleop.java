package com.qualcomm.ftcrobotcontroller.opmodes;
/**
 * Created by 6103 on 10/6/2015.
 * MainTeleOp program
 */

//Imports
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class MainTeleop extends OpMode{
    /*
        Members
     */
    //Drive Motors
    DcMotor leftDrive_wheel;
    DcMotor leftDrive_assembly;
    DcMotor rightDrive_wheel;
    DcMotor rightDrive_assembly;

    //---Constants---

    //Constant for assembly motor speed when triggers/bumpers are pressed
    final double assemblyDriveValue = 0.9;

    //Motor values
    double leftWheelValue = 0.0;
    double rightWheelValue = 0.0;

    /*
        Implementations
     */
    @Override
    public void init()
    {
        //Get refernces to wheel drive motors
        leftDrive_wheel = hardwareMap.dcMotor.get("drive_left_wheel");
        rightDrive_wheel = hardwareMap.dcMotor.get("drive_right_wheel");

        //get references to the assembly drive motors
        leftDrive_assembly = hardwareMap.dcMotor.get("drive_left_assembly");
        rightDrive_assembly = hardwareMap.dcMotor.get("drive_right_assembly");
    }

    @Override
    public void loop() {
        //Motor drive values
        leftWheelValue = gamepad1.left_stick_y;
        rightWheelValue = -(gamepad1.right_stick_y);

        //Write wheel values to motor
        leftDrive_wheel.setPower(leftWheelValue);
        rightDrive_wheel.setPower(rightWheelValue);

        //Write trigger value to assembly forward
        leftDrive_assembly.setPower((gamepad1.left_trigger > 0) ? assemblyDriveValue:0);
        rightDrive_assembly.setPower((gamepad1.right_trigger > 0) ? assemblyDriveValue:0);


        //Write bumper value to assembly backward
        leftDrive_assembly.setPower((gamepad1.left_bumper)? -(assemblyDriveValue):0);
        rightDrive_assembly.setPower((gamepad1.right_bumper) ? -(assemblyDriveValue):0);
    }



}
