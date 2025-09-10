
package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Locomocao;
import frc.robot.subsystems.Calcs;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;

public class RobotContainer {
  public DriveSubsystem driveSubsystem = new DriveSubsystem();

  private final Joystick bob = new Joystick (OperatorConstants.kDriverControllerPort);

  public Calcs calcs = new Calcs(bob);


  public RobotContainer() {
    driveSubsystem.setDefaultCommand(new Locomocao(driveSubsystem, calcs, bob));

  }

  public void configureBindings() {

      }

}
