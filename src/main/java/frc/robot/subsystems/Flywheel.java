/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {
  /**
   * Creates a new Flywheel.
   */
  //2 talons, 1 solenoid 

  private TalonSRX motor;
  private VictorSPX motor_follow;
  boolean solenoid_state=true;
  private Solenoid m_solenoid;

  private int launch_speed= -120000;

  public Flywheel() {
    m_solenoid= new Solenoid(Constants.transfer_solenoid_id);
    motor=new TalonSRX(2);
    motor_follow = new VictorSPX(9);
    motor_follow.follow(motor);
    motor_follow.configFactoryDefault();
  }

  public void fire_Proton_Torpedos(){
    motor.set(ControlMode.PercentOutput,-1.0);
    //motor.set(ControlMode.Velocity,launch_speed);
  }
  public void idle() {
    motor.set(ControlMode.PercentOutput,0.0);
  }

  public void toggle_transfer_solenoid(){
    m_solenoid.set(solenoid_state);
    solenoid_state= !solenoid_state;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   
    //motor.set(ControlMode.PercentOutput,0.0);
    
  }
}
