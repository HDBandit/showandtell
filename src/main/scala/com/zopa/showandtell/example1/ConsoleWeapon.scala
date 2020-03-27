package com.zopa.showandtell.example1

class ConsoleWeapon() extends Weapon {
  override def getCurrentPosition(): Position = {
    val currentPosition = Position(20, 30)
    println(s">> Get current position: $currentPosition")
    currentPosition
  }

  override def calculateRotationDegrees(currentPosition: Position, targetPosition: Position): Double = {
    val degreesToRotate = 34.45
    println(s">> Rotation degrees to rotate: $degreesToRotate")
    degreesToRotate
  }

  override def calculateElevationDegrees(currentPosition: Position, targetPosition: Position): Double = {
    val degreesToElevate = 34.45
    println(s">> Elevation degrees to elevate: $degreesToElevate")
    degreesToElevate
  }

  override def rotate(degrees: Double): Unit = {
    println(s"Degrees rotated: $degrees")
  }

  override def elevate(degrees: Double): Unit = {
    println(s"Degrees elevated: $degrees")
  }

  override def shoot(): Unit = {
    println("Shoot!!")
  }
}
