/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Disc_Spinner extends SubsystemBase {
  /**
   * Creates a new Disc_Spinner.
   */

  //1 motor, 1 solenoid
  private VictorSP spin_motor;
  private Solenoid m_solenoid;
  private boolean solenoid_state = false;

  public Disc_Spinner() {
    spin_motor = new VictorSP(Constants.spin_motor_id);
    m_solenoid = new Solenoid(Constants.disc_spinner_solenoid_id);
  }

  public void spin(double speed){
    spin_motor.set(speed);
  }


  public void toggle_solenoid(){
    m_solenoid.set(solenoid_state);
    solenoid_state = !solenoid_state;
  }

  @Override
  public void periodic() {
    spin_motor.set(0.0);
    // This method will be called once per scheduler run
  }
}
