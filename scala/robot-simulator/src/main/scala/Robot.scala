import Robot.{Bearing, RobotSimulator}

case class Robot(bearing: Bearing.TBearing, coordinates: (Int, Int)) {
  def turnLeft: Robot = RobotSimulator.turnLeft(this)
  def turnRight: Robot = RobotSimulator.turnRight(this)
  def advance: Robot = RobotSimulator.advance(this)
  def simulate(command: String): Robot = RobotSimulator.simulate(this)(command)
}

object Robot {
  object RobotSimulator {
    import Bearing._

    def turnLeft(robot: Robot): Robot = {
      val direction = robot.bearing match {
        case North => West
        case West => South
        case South => East
        case East => North
      }

      robot.copy(bearing = direction)
    }

    def turnRight(robot: Robot): Robot = {
      val direction = robot.bearing match {
        case North => East
        case East => South
        case South => West
        case West => North
      }

      robot.copy(bearing = direction)
    }

    def advance(robot: Robot): Robot = {
      import Bearing._

      val (x, y) = robot.coordinates
      val (offsetX, offsetY) = robot.bearing match {
        case North => (0, 1)
        case South => (0, -1)
        case West => (-1, 0)
        case East => (1, 0)
      }

      robot.copy(coordinates = (x + offsetX, y + offsetY))
    }

    def simulate(robot: Robot)(command: String): Robot = {
      command.foldLeft(robot) { (state, action) =>
        action match {
          case 'A' => advance(state)
          case 'R' => turnRight(state)
          case 'L' => turnLeft(state)
        }
      }
    }
  }

  object Bearing extends Enumeration {
    type TBearing = Value
    val North, South, East, West = Value
  }

}