package org.firstinspires.ftc.teamcode.constants;

public interface Constants {
    /** The servo in position for the whale (stake grabber)*/
    double WHALE_IN = 0.0;

    /** The servo out position without the stake*/
    double WHALE_OUT_WITH_STAKE = 0.35;

    /** The servo out position with the stake*/
    double WHALE_OUT_NO_STAKE = 0.6;

    /** Arm speed governor*/
    double ARM_GOVERNOR = 0.35;

    /** The starting config for the left claw*/
    double LEFT_CLAW_START = 1.0;

    /** The starting config for the right claw*/
    double RIGHT_CLAW_START = 0.0;

    /** The closed position for the left claw*/
    double LEFT_CLAW_CLOSED = 0.0;

    /** The open position for the left claw*/
    double LEFT_CLAW_OPEN = 0.5;

    /** The closed position for the right claw*/
    double RIGHT_CLAW_CLOSED = 1.0;

    /** The open position for the right claw*/
    double RIGHT_CLAW_OPEN = 0.5;

    /** Governor for drivetrain power*/
    double DRIVETRAIN_POWER_GOVERNOR = 0.8;

    /** Governor for drivetrain turn*/
    double DRIVETRAIN_TURN_GOVERNOR = 0.25;

    /** How many ticks per revolution are in a rev through bore encoder*/
    double TICKS_PER_REVOLUTION = 8192;

    /** Governor for the arm goToPosition()*/
    double GO_TO_GOVERNOR = 0.8;

    /** Angle offset for field oriented*/
    double ANGLE_OFFSET = Math.PI / 4;
}