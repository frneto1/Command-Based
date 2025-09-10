
package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autonomous;
import frc.robot.commands.Locomocao;
import frc.robot.subsystems.Calcs;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer{
  private final Joystick bob = new Joystick (OperatorConstants.kDriverControllerPort);
  public DriveSubsystem driveSubsystem = new DriveSubsystem();
  public Calcs calcs = new Calcs(bob);

  public Autonomous auto = new Autonomous(driveSubsystem, calcs);

  public RobotContainer() {
    driveSubsystem.setDefaultCommand(new Locomocao(driveSubsystem, calcs, bob));
    getAutonomous();
  }
  public void configureBindings() {

  }
  public Command getAutonomous(){
    return auto;
  }
  
}
