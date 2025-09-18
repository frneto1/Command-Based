
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Infravermelho extends SubsystemBase {
    public DigitalInput sensor = new DigitalInput(4);

    public boolean objDetectado(){
        return !sensor.get();
    }
}
