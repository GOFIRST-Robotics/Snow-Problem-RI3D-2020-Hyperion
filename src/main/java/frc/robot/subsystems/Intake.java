/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  //1 motor, 2 solenoid
  private boolean intake_engaged= false;

  private VictorSP m_motor_0;
  private VictorSP m_motor_1;
  private Solenoid solenoid_0;
  private Spark intake;

  public Intake() {
    intake= new Spark(Constants.intake_motor_id);
    solenoid_0= new Solenoid(Constants.intake_solenoid_id);
    m_motor_0= new VictorSP(Constants.transfer_motor_0_id);
    m_motor_1= new VictorSP(Constants.transfer_motor_1_id);
  }

  public void toggle_solenoid(){
    intake_engaged = !intake_engaged;
    solenoid_0.set(intake_engaged); 
  }

  public void spin_intake(double speed){
    intake.set(-speed);
    m_motor_0.set(speed);
    m_motor_1.set(-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    intake.set(0.0);
    m_motor_0.set(0.0);
    m_motor_1.set(-0.0);
      
  }
}
