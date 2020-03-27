package com.zopa.showandtell.example1

object App {
  def main(args: Array[String]): Unit = {
      val weapon: Weapon = new ConsoleWeapon()
      val attackManager = new AttackManager(weapon)

      attackManager.attack(Position(10, 10))
  }
}
