/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  //3 motors: 1 for deploy, 2 for winch

  private SpeedControllerGroup winch;
  private Spark leds;
  private VictorSP extend_motor;

  public Climber() {
    winch = new SpeedControllerGroup(new VictorSP(Constants.winch0_id), new VictorSP(Constants.winch1_id));
    extend_motor = new VictorSP(Constants.climb_extender_id);
    leds = new Spark(10);
  }

  public void extend_Claw(double speed){
    extend_motor.set(speed);
    leds.set(-0.97);
  }

  public void retract_Winch(double speed){
    winch.set(speed);
    leds.set(-0.97);
  }

  @Override
  public void periodic() {
    winch.set(0.0);
    leds.set(0.53);
    extend_motor.set(0.0);
    // This method will be called once per scheduler run
  }
}
