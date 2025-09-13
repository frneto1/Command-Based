package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {

    public Spark motorArm = new Spark(5);
    public Joystick roberto = new Joystick(1);

    public ArmSubsystem(){}
    

}
