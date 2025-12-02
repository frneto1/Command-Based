package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    
    public Joystick roberto = new Joystick(1);
    public static SparkMax motorPivot = new SparkMax(5, MotorType.kBrushless);
    public static SparkMax motorIntake = new SparkMax(6, MotorType.kBrushed);

    public static double velocidadeIntake;
    public static double velocidadePivot;

    public IntakeSubsystem(){}    
    
}
