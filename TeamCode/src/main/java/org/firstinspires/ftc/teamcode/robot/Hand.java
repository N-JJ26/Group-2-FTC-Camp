package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.constants.Constants;

/**
 * The hand of Cookie Monster
 *
 * @author Nate Johnson
 * @version 7/17/2024
 */
public class Hand implements Constants {
    public Servo handLeft, handRight;
    HardwareMap hwMap;
    Telemetry telemetry;

    /**
     * Instantiated the servos and telemetry of the hand
     *
     * @param hwMap the hardwareMap of the hand
     * @param telemetry the telemetry of the hand
     */
    public Hand(HardwareMap hwMap, Telemetry telemetry) {
        this.hwMap = hwMap;
        this.telemetry = telemetry;

        handLeft = hwMap.get(Servo.class, "handLeft");
        handRight = hwMap.get(Servo.class, "handRight");

        handLeft.setPosition(LEFT_CLAW_START);
        handRight.setPosition(RIGHT_CLAW_START);
    }

    /**
     * Toggles the state of the hand, either open or closed
     *
     * @param isClosed whether the hand is closed
     */
    public void toggleHand(boolean isClosed) {
        handLeft.setPosition(isClosed ? LEFT_CLAW_CLOSED : LEFT_CLAW_OPEN);
        handRight.setPosition(isClosed ? RIGHT_CLAW_CLOSED : RIGHT_CLAW_OPEN);

        telemetry.addData("isClosed", isClosed);
    }
}