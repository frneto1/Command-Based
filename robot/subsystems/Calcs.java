
package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU.GeneralStatus;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.commands.Locomocao;

public class Calcs {

    private Joystick bob;

    public Calcs(Joystick bob) {
        this.bob = bob;
    }

    public void LT() {
        double LT = bob.getRawAxis(2);
        Locomocao.velocidadeE = -Locomocao.m_speed * LT;
        Locomocao.velocidadeD = -Locomocao.m_speed * LT;
    }

    public void RT() {
        double RT = bob.getRawAxis(3);
        Locomocao.velocidadeE = Locomocao.m_speed * RT;
        Locomocao.velocidadeD = Locomocao.m_speed * RT;
    }

    public void pov() {
        int POV = bob.getPOV(0);

        switch (POV) {
            case 0:
                Locomocao.velocidadeE = Locomocao.m_speed;
                Locomocao.velocidadeD = Locomocao.m_speed;
                break;
            case 90:
                Locomocao.velocidadeE = Locomocao.m_speed;
                Locomocao.velocidadeD = -Locomocao.m_speed;
                break;
            case 180:
                Locomocao.velocidadeE = -Locomocao.m_speed;
                Locomocao.velocidadeD = -Locomocao.m_speed;
                break;
            case 270:
                Locomocao.velocidadeE = -Locomocao.m_speed;
                Locomocao.velocidadeD = Locomocao.m_speed;
                break;
            default:
                Locomocao.velocidadeE = 0;
                Locomocao.velocidadeD = 0;
        }
    }

    public void anaE() {

      double xe = bob.getRawAxis(0);
      double ye = bob.getRawAxis(1);

      double magnitude = Math.hypot(ye, xe);
  
      magnitude = Math.min(1, Math.max(-1, magnitude));
  
      double sen = ye/magnitude;
  
  
      
      if (ye < Constants.deadZone && xe > Constants.deadZone){
        Locomocao.velocidadeE = Locomocao.m_speed * magnitude;
        Locomocao.velocidadeD = (Locomocao.m_speed * (2 * sen + 1)) * magnitude * -1;
      } 
      else if (ye < Constants.deadZone && xe < -Constants.deadZone){
        Locomocao.velocidadeE = (Locomocao.m_speed * (2 * sen + 1)) * magnitude * -1;
        Locomocao.velocidadeD = Locomocao.m_speed * magnitude;
      }
      else if (ye > Constants.deadZone && xe < -Constants.deadZone){
        Locomocao.velocidadeE = -(Locomocao.m_speed * (2 * sen - 1)) * magnitude;
        Locomocao.velocidadeD = -Locomocao.m_speed * magnitude;
      }
      else if (ye > Constants.deadZone & xe > Constants.deadZone){
        Locomocao.velocidadeE = -Locomocao.m_speed * magnitude;
        Locomocao.velocidadeD = -(Locomocao.m_speed * (2 * sen - 1)) * magnitude;
      }
      else {
        Locomocao.velocidadeE = 0;
        Locomocao.velocidadeD = 0;
      }
    }
    public void anaD() {

      double xd = bob.getRawAxis(4);
      double yd = bob.getRawAxis(5);

      double magnitude = Math.sqrt(yd * yd + xd * xd);
      double sen = yd/magnitude;
  
      magnitude = Math.max(1, magnitude);

      
      if (yd < 0.04 && xd > 0.04){
        Locomocao.velocidadeE = -(Locomocao.m_speed * magnitude);
        Locomocao.velocidadeD = -(Locomocao.m_speed * (2 * sen + 1) * magnitude * -1);
      } 
      else if (yd < -0.04 && xd < 0.04){
        Locomocao.velocidadeE = -((Locomocao.m_speed * (2 * sen + 1)) * magnitude * -1);
        Locomocao.velocidadeD = -(Locomocao.m_speed * magnitude);
      }
      else if (yd > 0.04 && xd < 0.04){
        Locomocao.velocidadeE = (Locomocao.m_speed * (2 * sen - 1)) * magnitude;
        Locomocao.velocidadeD = Locomocao.m_speed * magnitude;
      }
      else if (yd > 0.04 & xd > 0.04){
        Locomocao.velocidadeE = Locomocao.m_speed * magnitude;
        Locomocao.velocidadeD = (Locomocao.m_speed * (2 * sen - 1)) * magnitude;
      }
      else {
        Locomocao.velocidadeE = 0;
        Locomocao.velocidadeD = 0;
      }
  
    }
}
