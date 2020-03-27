package com.zopa.showandtell.example2

import com.zopa.showandtell.example1.Position

object App {
  def main(args: Array[String]): Unit = {
     val attackProgram = Attack.attack3(Position(10, 10))
    ConsoleWeaponInterpreter.run(attackProgram)
  }
}
