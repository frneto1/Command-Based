


package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autonomous;
import frc.robot.commands.Locomocao;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.Calcs;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Infravermelho;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer{
  private final Joystick bob = new Joystick (OperatorConstants.kDriverControllerPort);
  private final Joystick roberto = new Joystick(1);
  public DriveSubsystem driveSubsystem = new DriveSubsystem();
  public ArmSubsystem as = new ArmSubsystem();
  public Infravermelho ir = new Infravermelho();
  public Calcs calcs = new Calcs(bob, roberto);

  public Autonomous auto = new Autonomous(driveSubsystem, ir);

  public RobotContainer() {
    driveSubsystem.setDefaultCommand(new Locomocao(driveSubsystem, calcs, bob, as, roberto));
    getAutonomous();
  }
  public void configureBindings() {

  }
  public Command getAutonomous(){
    return auto;
  }
  
}
