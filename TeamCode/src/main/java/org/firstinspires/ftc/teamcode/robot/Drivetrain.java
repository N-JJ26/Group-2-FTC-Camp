package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.constants.Constants;

/**
 * Drivetrain for Cookie Monster, the bot for the 2024 FTC camp
 *
 * @author Nate Johnson
 * @version 7/18/2024
 */
public class Drivetrain implements Constants {
    public DcMotor frontLeft, frontRight, backLeft, backRight;
    public Servo whale;
    public HardwareMap hwMap;
    public BNO055IMU imu;
    Telemetry telemetry;
    double frontLeftPower, frontRightPower, backLeftPower, backRightPower;

    /**
     * Instantiates all motors and servos, the imu, and the telemetry of the drivetrain
     *
     * @param hwMap the hardwareMap of the drivetrain
     * @param telemetry the telemetry of the drivetrain
     */
    public Drivetrain(HardwareMap hwMap, Telemetry telemetry) {
        this.hwMap = hwMap;
        this.telemetry = telemetry;

        frontLeft = hwMap.get(DcMotor.class, "red");
        frontRight = hwMap.get(DcMotor.class, "RED");
        backLeft = hwMap.get(DcMotor.class, "bleen");
        backRight = hwMap.get(DcMotor.class, "grue");

        whale = hwMap.get(Servo.class, "whale");

        whale.setPosition(WHALE_IN);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;

        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Moves the robot based off a given power, angle, and turn of the controller,
     *  as well as having a boolean to control the stake grabber (whale)
     *
     * @param power the drive power of the robot
     * @param angle the drive angle of the robot
     * @param turn the turning power of the robot
     * @param hasStake whether the robot has a stake or not
     */
    public void drive(double power, double angle, double turn,
                      boolean hasStake, boolean isIn) {
        if(isIn) {
            whale.setPosition(WHALE_IN);
        }
        else if(hasStake) {
            whale.setPosition(WHALE_OUT_WITH_STAKE);
        }
        else {
            whale.setPosition(WHALE_OUT_NO_STAKE);
        }

        frontLeftPower = power * Math.cos(angle + getHeading() + ANGLE_OFFSET) - turn;
        frontRightPower = -power * Math.sin(angle + getHeading() + ANGLE_OFFSET) - turn;
        backLeftPower = -power * Math.cos(angle + getHeading() + ANGLE_OFFSET) - turn;
        backRightPower = power * Math.sin(angle + getHeading() + ANGLE_OFFSET) - turn;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        telemetry.addData("power", power);
        telemetry.addData("angle (degrees)", angle * (180/Math.PI));
        telemetry.addData("angle (radians)", angle);
        telemetry.addData("turn", turn);
    }

    /**
     * Returns the current heading of the robot
     *
     * @return the current heading of the robot (rad)
     */
    public double getHeading() {
        return imu.getAngularOrientation().firstAngle;
    }
}