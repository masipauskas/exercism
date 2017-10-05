import scala.collection.SortedMap

class School {
  type DB = SortedMap[Int, Seq[String]]

  private [this] var dbInstance: DB = SortedMap()

  def add(name: String, g: Int) = {
    dbInstance = dbInstance + (g -> (grade(g) :+ name).distinct)
  }

  def db: DB = dbInstance

  def grade(g: Int): Seq[String] = dbInstance.getOrElse(g, Set()).toSeq

  def sorted: DB = dbInstance.mapValues(_.sorted)
}
