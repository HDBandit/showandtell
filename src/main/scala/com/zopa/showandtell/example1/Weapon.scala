package com.zopa.showandtell.example1

trait Weapon {
  def getCurrentPosition(): Position

  def calculateRotationDegrees(currentPosition: Position, targetPosition: Position): Double

  def calculateElevationDegrees(currentPosition: Position, targetPosition: Position): Double

  def rotate(degrees: Double): Unit

  def elevate(degrees: Double): Unit

  def shoot(): Unit
}
