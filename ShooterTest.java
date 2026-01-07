package com.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.teamcode.subsystems.ShooterSubsystem;

@TeleOp(name = "Shooter TeleOp", group = "Test")
public class ShooterTest extends LinearOpMode {

    private ShooterSubsystem shooter;

    @Override
    public void runOpMode() {

        // Create the subsystem
        shooter = new ShooterSubsystem(hardwareMap);

        telemetry.addLine("Shooter ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // Speed mode selection
            if (gamepad1.a) {
                shooter.setSpeedMode(ShooterSubsystem.SpeedMode.LOW);
            } else if (gamepad1.b) {
                shooter.setSpeedMode(ShooterSubsystem.SpeedMode.MEDIUM);
            } else if (gamepad1.y) {
                shooter.setSpeedMode(ShooterSubsystem.SpeedMode.MAX);
            }

            // Shoot command (example: right bumper)
            shooter.setShootCommand(gamepad1.right_bumper);

            // Update shooter every loop
            shooter.update();

            // Telemetry
            telemetry.addData("Mode", shooter.getSpeedMode());
            telemetry.addData("Target RPM", shooter.getTargetRPM());
            telemetry.addData("Current RPM", shooter.getVelocityRPM());
            telemetry.addData("At Speed", shooter.isAtSpeed());
            telemetry.update();
        }

        // Safe shutdown
        shooter.stop();
    }
}
