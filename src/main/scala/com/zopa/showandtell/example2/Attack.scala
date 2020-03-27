package com.zopa.showandtell.example2

import com.zopa.showandtell.example1.Position

object Attack {

  //DSL for combine ingredients
  sealed class AttackProgram[P] {
    def flatMap[V](f: P => AttackProgram[V]): AttackProgram[V] = RunAndThen(this, f)

    def map[V](f: P => V): AttackProgram[V] = flatMap((p: P) => Returns(f(p)))
  }

  case class Execute[P](action: WeaponAction[P]) extends AttackProgram[P]

  case class RunAndThen[P, V](program: AttackProgram[P], next: P => AttackProgram[V]) extends AttackProgram[V]

  case class Returns[P](value: P) extends AttackProgram[P]

  //DSL for ingredients
  sealed class WeaponAction[P]

  case object GetCurrentPosition extends WeaponAction[Position]

  case class CalculateRotationDegrees(currentPosition: Position, targetPosition: Position) extends WeaponAction[Double]

  case class CalculateElevationDegrees(currentPosition: Position, targetPosition: Position) extends WeaponAction[Double]

  case class Elevate(degrees: Double) extends WeaponAction[Unit]

  case class Rotate(degrees: Double) extends WeaponAction[Unit]

  case object Shoot extends WeaponAction[Unit]

  def attack(targePosition: Position): AttackProgram[Unit] = {
    RunAndThen(
      Execute(GetCurrentPosition), (currentPostion: Position) => {
        RunAndThen(
          Execute(CalculateRotationDegrees(currentPostion, targePosition)), (rotationDegrees: Double) => {
            RunAndThen(
              Execute(Rotate(rotationDegrees)), (_: Unit) => {
                RunAndThen(
                  Execute(CalculateElevationDegrees(currentPostion, targePosition)), (elevationDegrees: Double) => {
                    RunAndThen(
                      Execute(Elevate(elevationDegrees)), (_: Unit) => {
                        RunAndThen(
                          Execute(Shoot), (_: Unit) => {
                            Returns(())
                          }
                        )
                      }
                    )
                  }
                )
              }
            )
          }
        )
      }
    )
  }

  def attack2(targePosition: Position): AttackProgram[Unit] = {
    Execute(GetCurrentPosition).flatMap { currentPositon =>
      Execute(CalculateRotationDegrees(currentPositon, targePosition)).flatMap { rotationDegrees =>
        Execute(Rotate(rotationDegrees)).flatMap { _ =>
          Execute(CalculateElevationDegrees(currentPositon, targePosition)).flatMap { elevationDegrees =>
            Execute(Elevate(elevationDegrees)).flatMap { _ =>
              Execute(Shoot).flatMap { _ =>
                Returns(())
              }
            }
          }
        }
      }
    }
  }

  def attack3(targePosition: Position): AttackProgram[Unit] = for {
    currentPosition <- Execute(GetCurrentPosition)
    rotationDegrees <- Execute(CalculateRotationDegrees(currentPosition, targePosition))
    _ <- Execute(Rotate(rotationDegrees))
    elevationDegrees <- Execute(CalculateElevationDegrees(currentPosition, targePosition))
    _ <- Execute(Elevate(elevationDegrees))
    result <- Execute(Shoot)
  } yield result

}
