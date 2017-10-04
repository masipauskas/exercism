import SpaceAge.OrbitalPeriodScale._

import scala.math.BigDecimal.RoundingMode

object SpaceAge {
  val earthOrbitalPeriodPerSecond: Double = 31557600

  def onEarth(seconds: Double): Double = orbitalPeriodDuration(seconds, Earth)
  def onMercury(seconds: Double): Double = orbitalPeriodDuration(seconds, Mercury)
  def onVenus(seconds: Double): Double = orbitalPeriodDuration(seconds, Venus)
  def onMars(seconds: Double): Double = orbitalPeriodDuration(seconds, Mars)
  def onJupiter(seconds: Double): Double = orbitalPeriodDuration(seconds, Jupiter)
  def onSaturn(seconds: Double): Double = orbitalPeriodDuration(seconds, Saturn)
  def onUranus(seconds: Double): Double = orbitalPeriodDuration(seconds, Uranus)
  def onNeptune(seconds: Double): Double = orbitalPeriodDuration(seconds, Neptune)

  def orbitalPeriodDuration(seconds: Double, scale: OrbitalPeriodScale.Scale): Double = {
    BigDecimal((seconds / scale.scale) / earthOrbitalPeriodPerSecond)
      .setScale(2, RoundingMode.HALF_DOWN)
      .toDouble
  }

  object OrbitalPeriodScale {
    sealed abstract class Scale(val scale: Double)
    case object Earth extends Scale(1)
    case object Mercury extends Scale(0.2408467)
    case object Venus extends Scale(0.61519726)
    case object Mars extends Scale(1.8808158)
    case object Jupiter extends Scale(11.862615)
    case object Saturn extends Scale(29.447498)
    case object Uranus extends Scale(84.016846)
    case object Neptune extends Scale(164.79132)
  }
}
