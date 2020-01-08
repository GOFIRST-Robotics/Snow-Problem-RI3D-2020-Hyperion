/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Dummy_Test_System extends SubsystemBase {
  /**
   * Creates a new Dummy_Test_System.
   */

  private TalonSRX motor;

  public Dummy_Test_System() {
    motor=new TalonSRX(2);
  }

  public void move(double speed){
    motor.set(ControlMode.Velocity, 20000);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    motor.set(ControlMode.PercentOutput,0.0);
  }
}
