package com.zopa.showandtell.example2

import com.zopa.showandtell.example1.Position
import com.zopa.showandtell.example2.Attack._

object ConsoleWeaponInterpreter {
  def run[P](attackProgram: AttackProgram[P]): P = attackProgram match {
    case Execute(GetCurrentPosition) => {
      val currentPosition = Position(20, 30)
      println(s">> Get current position: $currentPosition")
      currentPosition
    }
    case Execute(CalculateRotationDegrees(currentPosition, targetPosition)) => {
      val degreesToRotate = 34.45
      println(s">> Rotation degrees to rotate: $degreesToRotate")
      degreesToRotate
    }
    case Execute(CalculateElevationDegrees(currentPosition, targetPosition)) => {
      val degreesToElevate = 34.45
      println(s">> Elevation degrees to elevate: $degreesToElevate")
      degreesToElevate
    }
    case Execute(Rotate(degrees)) => {
      println(s"Degrees rotated: $degrees")
    }
    case Execute(Elevate(degrees)) => {
      println(s"Degrees elevated: $degrees")
    }
    case Execute(Shoot) => {
      println("Shoot!!")
    }
    case Returns(value) => value
    case RunAndThen(program, next) => run(next(run(program)))
  }
}
