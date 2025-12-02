package frc.robot.commands;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Calcs;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.EjectorSubsystem;

public class Locomocao extends Command {
    public DriveSubsystem driveSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public Joystick bob;
    public Calcs calcs;
    private boolean a, b, x;
    private Joystick roberto;
    public static double velocidadeE = 0;
    public static double velocidadeD = 0;
    public static double m_speed;
    public static double velocidadeA;
    public EjectorSubsystem ejector;
    public double output;
    
    
        public Locomocao(DriveSubsystem driveSubsystem, Calcs calcs, Joystick bob, Joystick roberto, EjectorSubsystem ejector){
            this.driveSubsystem = driveSubsystem;
            this.bob = bob;
            this.roberto = roberto;
            this.calcs = calcs;
            this.ejector = ejector;
        
        addRequirements(driveSubsystem, ejector);    
    
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
        ControlIntake();
        ControlEjector();
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
    public void setSpeed(double velocidadeE, double velocidadeD) {

        // ------- real -------
        driveSubsystem.m_leftDrive.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_leftDrive2.set(ControlMode.PercentOutput, velocidadeE);
    
        driveSubsystem.m_rightDrive.set(ControlMode.PercentOutput, velocidadeD);
        driveSubsystem.m_rightDrive2.set(ControlMode.PercentOutput, velocidadeD);
    
        // ------- synthesis -------
        /*
        driveSubsystem.m_leftDrive.setPercentOutput(velocidadeE);
        driveSubsystem.m_leftDrive2.setPercentOutput(velocidadeE);
        driveSubsystem.m_rightDrive.setPercentOutput(velocidadeD);
        driveSubsystem.m_rightDrive2.setPercentOutput(velocidadeD);
        */
    }
    
    public void stop(){
        setSpeed(0, 0);
        IntakeSubsystem.motorIntake.set(0);
        IntakeSubsystem.motorPivot.set(0);
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


        if (bob.getPOV() != -1) {
            calcs.pov();
        }else if (RT <= Constants.deadZone && LT >= Constants.deadZone) {
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
        public void ControlIntake(){
            if (roberto.getRawAxis(2)  != 0){
                calcs.upPivot();
            } else if (roberto.getRawAxis(3) != 0) {
                calcs.runIntake();
            } else {
                stop();
            }
        }

        public void ControlEjector(){

            System.out.println(ejector.getRPM());

            double ff = ejector.kv * ejector.targetRPM;

            ejector.output = ff + ejector.pidOut;

            ejector.motorEjector.set(ejector.output);
            //ejector.motorEjector2.set(output);
            //ejector.motorEjector3.set(output);

        }


        public void dash(){

            SmartDashboard.putBoolean("A", a);
            SmartDashboard.putBoolean("B", b);
            SmartDashboard.putBoolean("X", x);
            SmartDashboard.putNumber("m_speed", m_speed);
            SmartDashboard.putNumber("Velocidade direita", velocidadeD);
            SmartDashboard.putNumber("Velocidade esquerda", velocidadeE);
            SmartDashboard.putNumber("Velocidade do pivot", IntakeSubsystem.motorPivot.get());
            SmartDashboard.putNumber("Velocidade do intake", IntakeSubsystem.motorIntake.get());
            SmartDashboard.putNumber("Shooter RPM", ejector.currentRPM);
            SmartDashboard.putNumber("Shooter Output", ejector.output);
        }
    }
