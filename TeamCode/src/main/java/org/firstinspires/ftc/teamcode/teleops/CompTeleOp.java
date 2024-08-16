package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.constants.Constants;
import org.firstinspires.ftc.teamcode.robot.Arm;
import org.firstinspires.ftc.teamcode.robot.Drivetrain;
import org.firstinspires.ftc.teamcode.robot.Hand;

/**
 * The TeleOp for the VEX High Stakes competition
 *
 * @author Nate Johnson
 * @version 7/18/2024
 */
@TeleOp(name = "Comp TeleOp", group = "Comp")
public class CompTeleOp extends OpMode implements Constants {
    private Drivetrain dt;
    private Arm arm;
    private Hand hand;
    double driverPower, operatorPower, angle, turn;
    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        dt = new Drivetrain(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);
        hand = new Hand(hardwareMap, telemetry);
    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        driverPower = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y) * DRIVETRAIN_POWER_GOVERNOR;
        angle = Math.atan2(gamepad1.left_stick_y , gamepad1.left_stick_x);
        turn = gamepad1.right_stick_x * DRIVETRAIN_TURN_GOVERNOR;

        dt.drive(driverPower, angle, turn, gamepad1.right_bumper, gamepad1.left_bumper);

        operatorPower = -gamepad2.left_stick_y;

        arm.moveArm(operatorPower);
        hand.toggleHand(gamepad2.b);

        if(gamepad2.left_bumper) {
            arm.goToPosition(-Math.PI);
        }
        if(gamepad2.right_bumper) {
            arm.goToPosition(-0.1105);
        }
    }
}