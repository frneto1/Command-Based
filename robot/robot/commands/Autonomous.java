


package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Infravermelho;
import frc.robot.subsystems.Limelight;

public class Autonomous extends Command {

    private final DriveSubsystem driveSubsystem;
    private final Infravermelho ir;
    private final Timer timer;
    public double velocidadeE;
    public double velocidadeD;
    private final double tempoLimite = 10.0;

    Limelight limelight = new Limelight("limelight-front");

    public Autonomous(DriveSubsystem driveSubsystem, Infravermelho ir, Limelight limelight) {
        this.driveSubsystem = driveSubsystem;
        this.timer = new Timer();
        this.ir = ir;
        this.limelight = limelight;

        addRequirements(driveSubsystem, ir);
    }

    @Override
    public void initialize() {
        driveSubsystem.reqDrive();
    }

    @Override
    public void execute() {
        System.out.println(limelight.getTv());
        System.out.println(limelight.getTa());
        if (limelight.getTv() != 0 && limelight.getTa() < 2){
                setSpeed(-0.25, -0.25);
            } else if (limelight.getTv() != 1){
                setSpeed(0.25, -0.25);
                }
            else if (limelight.getTa() >=  1 && limelight.getTv() != 0) {
                setSpeed(0, 0);
                }
            }
    @Override
    public void end(boolean interrupted) {
        stop();
    }

    @Override
    public boolean isFinished() {
        return false;
        }

    public void setSpeed(double velocidadeE, double velocidadeD) {
        // ------- real -------


        driveSubsystem.m_leftDrive.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_leftDrive2.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_rightDrive.set(ControlMode.PercentOutput, velocidadeD);
        driveSubsystem.m_rightDrive2.set(ControlMode.PercentOutput, velocidadeD);

         // ------- synthesis -------
         //driveSubsystem.m_leftDrive.setPercentOutput(velocidadeE);
        // driveSubsystem.m_leftDrive2.setPercentOutput(velocidadeE);
     
        // driveSubsystem.m_rightDrive.setPercentOutput(velocidadeD);
        // driveSubsystem.m_rightDrive2.setPercentOutput(velocidadeD);
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
