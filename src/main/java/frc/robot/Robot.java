// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;


public class Robot extends TimedRobot {

   Joystick Controle = new Joystick(0);

//Locomoção
   SparkMax MotorEsqF = new SparkMax(4, MotorType.kBrushed); 
   SparkMax MotorEsqT = new SparkMax(5, MotorType.kBrushed); 
   SparkMax MotorDirF = new SparkMax(6, MotorType.kBrushed); 
   SparkMax MotorDirT = new SparkMax(7, MotorType.kBrushed);


//Atirador de bolas
   SparkMax MotorColAti = new SparkMax(44, MotorType.kBrushed); 
   SparkMax MotorAtiConstante = new SparkMax(55, MotorType.kBrushed); 

// O TempoInicio serve para que quando iniciar o modo autonomo o tempo de quando o robo foi iniciado não interfira.
   double TempoInicio;

  public Robot() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    TempoInicio = Timer.getFPGATimestamp();
  }
  
  @Override
  public void autonomousPeriodic() {
    double Tempo = Timer.getFPGATimestamp();

      if (Tempo - TempoInicio < 2) {
       MotorAtiConstante.set(1);
      
      } else if (Tempo - TempoInicio < 4){
      MotorAtiConstante.set(1);
       MotorColAti.set(1);
      } else {
       MotorAtiConstante.set(0);
       MotorColAti.set(0);
      }

   
    // É para fazer: Coletar em linha reta de costas, Andar para frente, Girar para a direita(depende), Atirar as bolas armazenadas, Parar;
    /*if (Tempo - TempoInicio < 2) {
      MotorEsqF.set(-0.5);
      MotorDirF.set(-0.5);
      MotorEsqT.set(-0.5);
      MotorDirT.set(-0.5);
      MotorColAti.set(-0.8);
      MotorAtiConstante.set(0.8);

    } else if (Tempo - TempoInicio < 3) {

      MotorEsqF.set(-0.25);
      MotorDirF.set(-0.25);
      MotorEsqT.set(-0.25);
      MotorDirT.set(-0.25);
      MotorColAti.set(-1);
      MotorAtiConstante.set(1);

    }

    else if (Tempo - TempoInicio < 4) {
      MotorEsqF.set(0.25);
      MotorDirF.set(0.25);
      MotorEsqT.set(0.25);
      MotorDirT.set(0.25);
      MotorColAti.set(0);
      MotorAtiConstante.set(1);

    } else if (Tempo - TempoInicio < 6) {
      MotorEsqF.set(0);
      MotorDirF.set(0);
      MotorEsqT.set(0);
      MotorDirT.set(0);
      MotorAtiConstante.set(1);


    } else if (Tempo - TempoInicio < 8) {
      MotorAtiConstante.set(1);
      MotorColAti.set(1);

    } else {

      MotorDirF.set(0);
      MotorEsqF.set(0);
      MotorEsqT.set(0);
      MotorDirT.set(0);
      MotorColAti.set(0);
      MotorAtiConstante.set(0);
  
    }*/

  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
  double MovFrTr = Controle.getRawAxis(1); //Direcional esquerdo
  double MovLado = Controle.getRawAxis(4); //Direcional direito
  double Atirar = Controle.getRawAxis(3); //Gatilho Direito, atirador;
  double Coleta = Controle.getRawAxis(2); //Gatilho Esquero, Coletar;

  if (Coleta > 0.5 && Atirar > 0.5){
    MotorAtiConstante.set(0);
    MotorColAti.set(0);

  } else if (Coleta > 0.5) {

    MotorAtiConstante.set(1);
    MotorColAti.set(-1);

  } else if (Atirar > 0.5) {

    MotorColAti.set(0.6);
    MotorAtiConstante.set(0.9);

  } else {
    MotorColAti.set(0);
      MotorAtiConstante.set(0);
  }
  
  /*MotorColAti.set(Atirar * 0.8);
  MotorAtiConstante.set(Atirar * 0.8);
  MotorAtiConstante.set(Atirar * 0.8);*/

  double Esquerda = MovFrTr - MovLado;
  double Direita = MovFrTr + MovLado;

  MotorEsqF.set(Esquerda * 0.6);
  MotorEsqT.set(Esquerda * 0.6);
  MotorDirF.set(Direita * 0.6);
  MotorDirT.set(Direita * 0.6);

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
