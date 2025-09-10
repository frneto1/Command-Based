
package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Calcs;
import frc.robot.subsystems.DriveSubsystem;

public class Locomocao extends Command {
    public DriveSubsystem driveSubsystem;
    public Joystick bob;
    public Calcs calcs;
    private boolean a, b, x;
    public static double velocidadeE;
    public static double velocidadeD;
    public static double m_speed;


    public Locomocao(DriveSubsystem driveSubsystem, Calcs calcs, Joystick bob){
        this.driveSubsystem = driveSubsystem;
        this.bob = bob;
        this.calcs = calcs;
        
        addRequirements(driveSubsystem);
    
    }
    @Override
    public void initialize(){
        driveSubsystem.reqDrive();
    }
    @Override
    public void execute(){
        button();
        setSpeed(velocidadeE, velocidadeD);
        control();
        dash();
    }
    @Override
    public void end(boolean interrupted){
        stop();
    }
    @Override
    public boolean isFinished(){
        return false;
    }
    public void setSpeed(double velocidadeE, double velocidadeD){
        driveSubsystem.m_leftDrive.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_leftDrive2.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_rightDrive.set(ControlMode.PercentOutput, velocidadeD);
        driveSubsystem.m_rightDrive2.set(ControlMode.PercentOutput, velocidadeD);     
    }
    public void stop(){
        setSpeed(0, 0);
    }
    public void button(){
       a = bob.getRawButton(1);
       b = bob.getRawButton(2);
       x = bob.getRawButton(3);

        if (a){
            m_speed = 0.25;
        }
        else if (b){
            m_speed = 0.50;
        }
        else if (x){
            m_speed = 1;
        }        
    }
    
    public void control() {
        double LT = bob.getRawAxis(2);
        double RT = bob.getRawAxis(3);

        double xe = bob.getRawAxis(0);
        double ye = bob.getRawAxis(1);
        double xd = bob.getRawAxis(4);
        double yd = bob.getRawAxis(5);

        if (bob.getPOV() != -1) {
            calcs.pov();
        } else if (RT <= Constants.deadZone && LT >= Constants.deadZone) {
            calcs.LT();
        } else if (LT <= Constants.deadZone && RT >= Constants.deadZone) {
            calcs.RT();
        } else {
         if (xe > Constants.deadZone || ye > Constants.deadZone || xe < -Constants.deadZone || ye < -Constants.deadZone) {
                calcs.anaE();
        } else {
                calcs.anaD();
        }
    }
        }
        public void dash(){
            SmartDashboard.putBoolean("A", a);
            SmartDashboard.putBoolean("B", b);
            SmartDashboard.putBoolean("X", x);
            SmartDashboard.putNumber("m_speed", m_speed);
            SmartDashboard.putNumber("Velocidade direita", velocidadeD);
            SmartDashboard.putNumber("Velocidade esquerda", velocidadeE);
        }
    }
