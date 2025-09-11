

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.infravermelho;

public class Autonomous extends Command {

    private final DriveSubsystem driveSubsystem;
    private final infravermelho ir;
    private final Timer timer;
    private double velocidadeE;
    private double velocidadeD;
    private final double tempoLimite = 10.0; 

    public Autonomous(DriveSubsystem driveSubsystem, infravermelho ir) {
        this.driveSubsystem = driveSubsystem;
        this.timer = new Timer();
        this.ir = ir;

        addRequirements(driveSubsystem, ir);
    }

    @Override
    public void initialize() {
        driveSubsystem.reqDrive();
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if (ir.objDetectado()){
        stop();
        } else {
        System.out.println("Valor IR" + ir.sensor.get());
        dash();
        setSpeed(velocidadeE, velocidadeD);
        Control();
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
        driveSubsystem.m_leftDrive.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_leftDrive2.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_rightDrive.set(ControlMode.PercentOutput, velocidadeD);
        driveSubsystem.m_rightDrive2.set(ControlMode.PercentOutput, velocidadeD);
    }

    public void stop() {
        setSpeed(0, 0);
    }

    public void Control() {
        velocidadeE = 0.25;
        velocidadeD = 0.25;
    }

    public void dash() {
        SmartDashboard.putNumber("Velocidade direita", velocidadeD);
        SmartDashboard.putNumber("Velocidade esquerda", velocidadeE);
        SmartDashboard.putNumber("Tempo", timer.get());
    }
}
