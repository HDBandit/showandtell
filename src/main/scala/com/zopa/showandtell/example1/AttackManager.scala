package com.zopa.showandtell.example1

case class Position(x: Int, y: Int)

class AttackManager(weapon: Weapon) {

  def attack(target: Position): Unit = {
    val currentPosition = weapon.getCurrentPosition()
    val degreesToRotate = weapon.calculateRotationDegrees(currentPosition, target)
    weapon.rotate(degreesToRotate)
    val degreesToElevate = weapon.calculateElevationDegrees(currentPosition, target)
    weapon.elevate(degreesToElevate)
    weapon.shoot()
  }
}
