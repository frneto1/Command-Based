package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

    public VictorSPX m_leftDrive = new VictorSPX(3);
    public VictorSPX m_rightDrive = new VictorSPX(2);
    public VictorSPX m_leftDrive2 = new VictorSPX(4);
    public VictorSPX m_rightDrive2 = new VictorSPX(1);

    public DriveSubsystem(){
        reqDrive();
    }
    public void reqDrive(){

        m_rightDrive.setInverted(true);
        m_rightDrive2.setInverted(true);

        m_rightDrive.configNeutralDeadband(0.04);
        m_rightDrive2.configNeutralDeadband(0.04);
        m_leftDrive.configNeutralDeadband(0.04);
        m_leftDrive2.configNeutralDeadband(0.04);

        m_rightDrive.setNeutralMode(NeutralMode.Brake);
        m_rightDrive2.setNeutralMode(NeutralMode.Brake);
        m_leftDrive.setNeutralMode(NeutralMode.Brake);
        m_leftDrive2.setNeutralMode(NeutralMode.Brake);

    }
}
