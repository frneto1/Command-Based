package frc.robot.subsystems;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase{

    public PIDController pidClimb = new PIDController(0.002, 0.0, 0.0);
    public double targetPositionClimb = 1;
    public RelativeEncoder climbEncoder;
    public double pidOut;

    //verificar depois se é NEO ou REDLINE e o ID
    public SparkMax motorClimb = new SparkMax(11, SparkMax.MotorType.kBrushless);
    public SparkMax motorClimb2 = new SparkMax(12, SparkMax.MotorType.kBrushless);

    public ClimbSubsystem() {

        SparkMaxConfig config = new SparkMaxConfig();
        config.idleMode(IdleMode.kBrake);
        config.inverted(true);

        SparkMaxConfig config2 = new SparkMaxConfig();
        config2.idleMode(IdleMode.kBrake);
        config2.inverted(false);

        motorClimb.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorClimb2.configure(config2, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        climbEncoder = motorClimb.getEncoder();
    }

    public void periodic() {
        pidOut = pidClimb.calculate(climbEncoder.getPosition(), targetPositionClimb);
    }

}