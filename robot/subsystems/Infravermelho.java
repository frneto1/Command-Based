package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class infravermelho extends SubsystemBase {
    public DigitalInput sensor = new DigitalInput(0);

    public boolean objDetectado(){
        return !sensor.get();
    }
}
