
package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Calcs;
import frc.robot.subsystems.DriveSubsystem;

public class Autonomous extends Command {

    private final DriveSubsystem driveSubsystem;
    private final Calcs calcs;
    private final Timer timer;
    private double velocidadeE;
    private double velocidadeD;
    private final double tempoLimite = 15.0; 

    public Autonomous(DriveSubsystem driveSubsystem, Calcs calcs) {
        this.driveSubsystem = driveSubsystem;
        this.calcs = calcs;
        this.timer = new Timer();
    }

    @Override
    public void initialize() {
        driveSubsystem.reqDrive();
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        dash();
    if (timer.get < 2.0) { //exemplo
        setSpeed(velocidadeE, velocidadeD);
     } else {
       stop();
      }
    }  

    @Override
    public void end(boolean interrupted) {
        stop();
    }

    @Override
    public boolean isFinished() {
        return timer.get() > tempoLimite;
    }

    public void setSpeed(double velocidadeE, double velocidadeD) {
        velocidadeE = 0.25;
        velocidadeD = 0.25;
        driveSubsystem.m_leftDrive.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_leftDrive2.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_rightDrive.set(ControlMode.PercentOutput, velocidadeD);
        driveSubsystem.m_rightDrive2.set(ControlMode.PercentOutput, velocidadeD);
    }

    public void stop() {
        setSpeed(0, 0);
    }


    public void dash() {
        SmartDashboard.putNumber("Velocidade direita", velocidadeD);
        SmartDashboard.putNumber("Velocidade esquerda", velocidadeE);
        SmartDashboard.putNumber("Tempo", timer.get());
    }
}
