package command

trait CommandSerializer {

  def serialize(commands: Seq[PlayerCommand]): String

}

class SimpleCommandSerializer extends CommandSerializer {
  override def serialize(commands: Seq[PlayerCommand]): String = commands.map(_.toString).mkString("|")
}
