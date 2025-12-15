


package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autonomous;
import frc.robot.commands.Locomocao;
import frc.robot.subsystems.Calcs;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.EjectorSubsystem;
import frc.robot.subsystems.Infravermelho;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer{
  public Limelight limelight = new Limelight("limelight-front");
  private final Joystick bob = new Joystick (OperatorConstants.kDriverControllerPort);
  private final Joystick roberto = new Joystick(1);
  public DriveSubsystem driveSubsystem = new DriveSubsystem();
  public Infravermelho ir = new Infravermelho();
  public Calcs calcs = new Calcs(bob, roberto);
  public EjectorSubsystem ejector = new EjectorSubsystem();
  public ClimbSubsystem climber = new ClimbSubsystem();

  public Autonomous auto = new Autonomous(driveSubsystem, ir, limelight, ejector);

  public RobotContainer() {
    driveSubsystem.setDefaultCommand(new Locomocao(driveSubsystem, calcs, bob, roberto, ejector, ir, climber));
    getAutonomous();
  }
  public void configureBindings() {

  }
  public Command getAutonomous(){
    return auto;
  }
  
}
