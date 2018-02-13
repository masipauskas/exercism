import Plant.Plant

class Garden(studentGardens: Map[String, Seq[Plant]]) {
  def plants(name: String): Seq[Plant] = studentGardens(name)
}

object Garden {
  private val defaultStudents = Seq(
    "Alice", "Bob", "Charlie", "David",
    "Eve", "Fred", "Ginny", "Harriet",
    "Ileana", "Joseph", "Kincaid", "Larry"
  )
  def defaultGarden(layout: String): Garden = Garden(defaultStudents, layout)

  def apply(students: Seq[String], layout: String): Garden = {
    def rowToGardenRow(row: String): IndexedSeq[Seq[Plant]] = {
      row.map(c => Plant.withName(c.toString))
        .grouped(2)
        .toIndexedSeq
    }

    val gardenLayout = layout
      .split("\n")
      .map(rowToGardenRow)

    val studentsInOrder = students.sorted
    val firstRow = gardenLayout.head
    val secondRow = gardenLayout.tail.head

    val studentGardens = firstRow.zip(secondRow).zip(studentsInOrder)

    val typedStudentGardens = for {
      ((first, second), student) <- studentGardens
    } yield student -> (first ++ second)

    new Garden(typedStudentGardens.toMap.withDefaultValue(Nil))
  }
}

object Plant extends Enumeration {
  type Plant = Value
  val Violets: Plant.Value = Value("V")
  val Clover: Plant.Value = Value("C")
  val Radishes: Plant.Value = Value("R")
  val Grass: Plant.Value = Value("G")
}