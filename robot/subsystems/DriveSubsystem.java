package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// --------- synthesis ---------
import com.autodesk.synthesis.CANMotor;

public class DriveSubsystem extends SubsystemBase {

    // --------- real ---------
    public VictorSPX m_leftDrive = new VictorSPX(3);
    public VictorSPX m_rightDrive = new VictorSPX(2);
    public VictorSPX m_leftDrive2 = new VictorSPX(4);
    public VictorSPX m_rightDrive2 = new VictorSPX(1);

    // --------- synthesis ---------
    /*
    public CANMotor m_leftDrive = new CANMotor("Left1", 3, 0.0, true, 0.04);
    public CANMotor m_rightDrive = new CANMotor("Right1", 2, 0.0, true, 0.04);
    public CANMotor m_leftDrive2 = new CANMotor("Left2", 4, 0.0, true, 0.04);
    public CANMotor m_rightDrive2 = new CANMotor("Right2", 1, 0.0, true, 0.04);
    */

    private final Encoder encoder = new Encoder(5, 4);

    public DriveSubsystem() {
        reqDrive();
    }

    public void reqDrive() {

        // ------------- real -------------
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
        // ------------- synthesis -------------
        /*
        m_leftDrive.setBrakeMode(true);
        m_rightDrive.setBrakeMode(true);
        m_leftDrive2.setBrakeMode(true);
        m_rightDrive2.setBrakeMode(true);

        m_leftDrive.setNeutralDeadband(0.04);
        m_rightDrive.setNeutralDeadband(0.04);
        m_leftDrive2.setNeutralDeadband(0.04);
        m_rightDrive2.setNeutralDeadband(0.04);
         */
       
        double distancePerPulse = (Math.PI * 0.06) / 2048;
        encoder.setDistancePerPulse(distancePerPulse);
    }

    public double getEncoderDistance() { return encoder.getDistance(); }

    public void resetEncoder() { encoder.reset(); }
}
