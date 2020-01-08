/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Drive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Disc_Spinner;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final Dummy_Test_System m_testbed = new Dummy_Test_System();

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Disc_Spinner m_disc_spinner = new Disc_Spinner();
  private final Climber m_climber = new Climber();
  private final Intake m_intake = new Intake(); 
  private final Flywheel m_flywheel= new Flywheel();
  
  private final Joystick m_joystick = new Joystick(1);
  
  private final Drive m_drive = new Drive(m_drivetrain,m_joystick);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drivetrain.setDefaultCommand(m_drive);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new Trigger(() -> m_joystick.getPOV() == 90).whileActiveContinuous(new RunCommand( () -> m_disc_spinner.spin(0.5) , m_disc_spinner));
    new Trigger(() -> m_joystick.getPOV() == 270).whileActiveContinuous(new RunCommand( () -> m_disc_spinner.spin(-0.5) , m_disc_spinner));
    new Trigger(() -> m_joystick.getPOV() == 0).whileActiveOnce(new InstantCommand( () -> m_disc_spinner.toggle_solenoid() , m_disc_spinner));

    new JoystickButton(m_joystick, 2).whileHeld(new RunCommand( () -> m_intake.spin_intake(-1.0) , m_intake));
    new JoystickButton(m_joystick, 6).whileHeld(new RunCommand( () -> m_intake.spin_intake(1.0) , m_intake));
    new JoystickButton(m_joystick, 3).whenPressed(new InstantCommand( () -> m_intake.toggle_solenoid() , m_intake));

    new JoystickButton(m_joystick, 7).whileHeld(new RunCommand( () -> m_flywheel.fire_Proton_Torpedos() , m_flywheel)).whenInactive(new RunCommand( () -> m_flywheel.idle() , m_flywheel));
    new JoystickButton(m_joystick, 5).whenPressed(new InstantCommand( () -> m_flywheel.toggle_transfer_solenoid() , m_flywheel));

    
    //commands to climb and reset mechanism
    new JoystickButton(m_joystick, 1).whileHeld(new RunCommand( () -> m_climber.extend_Claw(0.40) , m_climber));
    new JoystickButton(m_joystick, 4).whileHeld(new RunCommand( () -> m_climber.extend_Claw(-0.10) , m_climber));


    new JoystickButton(m_joystick, 9).whileHeld(new RunCommand( () -> m_climber.retract_Winch(1) , m_climber));
    new JoystickButton(m_joystick, 10).whileHeld(new RunCommand( () -> m_climber.retract_Winch(-1) , m_climber));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_drive;
  }
}
