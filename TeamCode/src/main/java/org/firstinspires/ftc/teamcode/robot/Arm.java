package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.constants.Constants;

/**
 * The arm of the 2024 FTC Camp robot
 *
 * @author Nate Johnson
 * @version 7/18/2024
 */
public class Arm implements Constants {
    public DcMotorEx shoulderLeft, shoulderRight, encoder;
    HardwareMap hwMap;
    Telemetry telemetry;

    /**
     * Instantiates all motors and telemetry in the arm
     *
     * @param hwMap the hardwareMap of the arm
     * @param telemetry the telemetry to be arm
     */
    public Arm(HardwareMap hwMap, Telemetry telemetry) {
        this.hwMap = hwMap;
        this.telemetry = telemetry;

        shoulderLeft = hwMap.get(DcMotorEx.class, "shoulderLeft");
        shoulderRight = hwMap.get(DcMotorEx.class, "shoulderRight");
        encoder = hwMap.get(DcMotorEx.class, "encoder");

        shoulderLeft.setDirection(DcMotorEx.Direction.FORWARD);
        shoulderRight.setDirection(DcMotorEx.Direction.FORWARD);

        shoulderLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        shoulderRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        shoulderLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shoulderRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        shoulderLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        shoulderRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        //while(goToPosition(0)) {}
    }

    /**
     * Moves the arm based off of an inputted power
     *
     * @param power the power of the arm movement
     */
    public void moveArm(double power) {
        shoulderLeft.setPower(power * ARM_GOVERNOR);
        shoulderRight.setPower(power * ARM_GOVERNOR);

        telemetry.addData("power", power);
        telemetry.addData("arm pos", encoder.getCurrentPosition());
        telemetry.addData("arm angle", getArmAngle());
    }

    /**
     * Rotates the arm to a given angle (rad)
     *
     * @param target the target angle to move to
     * @return true if the arm has reached the target, false otherwise
     */
    public boolean goToPosition(double target) {
        double error = target - getArmAngle();
        moveArm(GO_TO_GOVERNOR * error);
        telemetry.addData("arm angle", getArmAngle());
        return error < 0.15;
    }

    private double getArmAngle() {
        return encoder.getCurrentPosition() * ((2 * Math.PI) / TICKS_PER_REVOLUTION);
    }
}