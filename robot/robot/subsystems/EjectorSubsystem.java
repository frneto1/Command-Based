package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EjectorSubsystem extends SubsystemBase {

    public double currentRPM;
    public double pidOut;
    public double output;

    public SparkMax motorEjector = new SparkMax(7, SparkMax.MotorType.kBrushed);
    public SparkMax motorEjector2 = new SparkMax(8, SparkMax.MotorType.kBrushed);
    public SparkMax motorEjector3 = new SparkMax(9, SparkMax.MotorType.kBrushed);

    Encoder encoder = new Encoder(1, 2);
    
    public EjectorSubsystem() {
        double distancePerPulse = (Math.PI * 0.06) / 2048;
        encoder.setDistancePerPulse(distancePerPulse);

    }

    public double targetRPM;
    double rpm = encoder.getRate() * 60;
    public double kP = 0;
    public double kI = 0;
    public double kD = 0;
    public double kv = 0.00004761904; // confirmar depois com o robô
    public PIDController pid = new PIDController(kP, kI, kD);

    public void setRPM(double rpm) {
        targetRPM = rpm;
    }

    public double getRPM() {
        return encoder.getRate() * 60;
    }

    public void periodic() {
        rpm = encoder.getRate() * 60;
        double currentRPM = getRPM();
        pidOut = pid.calculate(currentRPM, targetRPM);

        double ff = kv * targetRPM;
        output = ff + pidOut;

        motorEjector.set(output);
        motorEjector2.set(output);
        motorEjector3.set(output);
    }
}
