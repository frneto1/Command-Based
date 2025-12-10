


package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.EjectorSubsystem;
import frc.robot.subsystems.Infravermelho;
import frc.robot.subsystems.Limelight;

public class Autonomous extends Command {

    double X0, Y0;
    double X1, Y1;
    double X2, Y2;

    private final DriveSubsystem driveSubsystem;
    private final Infravermelho ir;
    public Timer timer;
    public double velocidadeE;
    public double velocidadeD;
    private EjectorSubsystem ejector;
    public double tempoLimite = 4;
    private final PIDController limelightPID = new PIDController(0.0, 0.0, 0.0);
    public double p0;
    public double p1;
    public double p2;
    public double t;

    double targetX;
    double targetY;



    Limelight limelight = new Limelight("limelight-front");

    public Autonomous(DriveSubsystem driveSubsystem, Infravermelho ir, Limelight limelight, EjectorSubsystem ejector) {
        this.driveSubsystem = driveSubsystem;
        this.timer = new Timer();
        this.ir = ir;
        this.limelight = limelight;
        this.ejector = ejector;
      
        addRequirements(driveSubsystem, ir);
    }

    @Override
    public void initialize() {
        cordenadas();
        driveSubsystem.reqDrive();
        timer.start();
    }

    @Override
    public void execute() {
        t = timer.get() / tempoLimite;
        if (t > 1) t = 1;
        curvinha();
        ControlEjector();
    }
    @Override
    public void end(boolean interrupted) {
        stop();
        timer.reset();
    }

    @Override
    public boolean isFinished() {
        return false;
        }

    public void setSpeed(double velocidadeE, double velocidadeD) {
        // ------- real -------
/*
        driveSubsystem.m_leftDrive.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_leftDrive2.set(ControlMode.PercentOutput, velocidadeE);
        driveSubsystem.m_rightDrive.set(ControlMode.PercentOutput, velocidadeD);
        driveSubsystem.m_rightDrive2.set(ControlMode.PercentOutput, velocidadeD);
*/

         // ------- synthesis -------
         
         driveSubsystem.m_leftDrive.setPercentOutput(velocidadeE);
         driveSubsystem.m_leftDrive2.setPercentOutput(velocidadeE);
     
         driveSubsystem.m_rightDrive.setPercentOutput(velocidadeD);
         driveSubsystem.m_rightDrive2.setPercentOutput(velocidadeD);
         
    }

    public void stop() {
        setSpeed(0, 0);
    }
    
    public void dash() {
        SmartDashboard.putNumber("Velocidade direita", velocidadeD);
        SmartDashboard.putNumber("Velocidade esquerda", velocidadeE);
        SmartDashboard.putNumber("Tempo", timer.get());
    }

    public void ControlLimelight() {

        double tv = limelight.getTv();
        double ta = limelight.getTa();
        double tx = limelight.getTx();
    
        if (tv == 0) {
            setSpeed(0.5, -0.5);
            return;
        }
    
        if (ta > 1.5) {
            setSpeed(0, 0);
            return;
        }
    
        double turn = limelightPID.calculate(tx, 0);
    
        setSpeed(0.3 + turn, 0.3 - turn);
    }
    

    public void ControlEjector(){

        if (ir.objDetectado()){
            ejector.motorEjector.set(0.3);
            ejector.motorEjector2.set(-0.3);
            ejector.motorEjector3.set(-0.3);

        } else {
            ejector.motorEjector.set(0);
            ejector.motorEjector2.set(0);
            ejector.motorEjector3.set(0);

        }
    }

    public void curvinha() {

        targetX = Math.pow(1 - t, 2) * X0 + 2 * (1 - t) * t * X1 + t * t * X2;
        targetY = Math.pow(1 - t, 2) * Y0 + 2 * (1 - t) * t * Y1 + t * t * Y2;
    
        double forward = targetY * 0.3;  
        double turn = targetX * 0.6;
    
        double left = forward + turn;
        double right = forward - turn;
    
        left = Math.max(-1, Math.min(1, left));
        right = Math.max(-1, Math.min(1, right));
    
        setSpeed(left, right);
    }
    
    public void cordenadas(){
        X0 = 0;
        Y0 = 0;
        X1 = 1.0;
        Y1 = 2.0;
        X2 = 0;
        Y2 = 3.0;
    }
    
}
