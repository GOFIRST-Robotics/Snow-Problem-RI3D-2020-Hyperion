/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  //private DifferentialDrive drivetrain;
  private VictorSP[] motors;
  private TalonSRX talon;
  private SpeedControllerGroup right_motors;
  //private SpeedControllerGroup left_motors;

  public Drivetrain() {
    motors= new VictorSP[] { new VictorSP(Constants.fl_motor_id),
      new VictorSP(Constants.rr_motor_id), new VictorSP(Constants.fr_motor_id)};
    talon =new TalonSRX(5);
    right_motors = new SpeedControllerGroup(motors[1], motors[2]);
    //drivetrain = new DifferentialDrive(left_motors, right_motors);
  }

  public void drive(double l, double r){
    


    talon.set(ControlMode.PercentOutput,l);
    motors[0].set(l);

    right_motors.set(r);
    
    SmartDashboard.putNumber("r", r);
    SmartDashboard.putNumber("l", l);
  }

  public void drive(Joystick joy){
    double fwd = -joy.getY(Hand.kLeft);
    double turn = joy.getRawAxis(2);
    fwd = Math.copySign(fwd*fwd, fwd);
    turn = Math.copySign(turn*turn, turn) * 0.5;
    double l = fwd + turn;
    double r = fwd - turn;
    drive(l,r);
  }

  public void _StAAapP(){
    for (VictorSP t : motors) {
      t.set(0);
    } 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
