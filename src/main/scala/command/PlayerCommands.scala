package command

trait PlayerCommand

case class Move(dx: Int, dy: Int) extends PlayerCommand {
  override def toString = "Move(direction=%s:%s)".format(dx, dy)
}

case class Spawn(dx: Int, dy: Int, name: String, energy: Int) extends PlayerCommand {
  override def toString = "Spawn(direction=%d:%d,name=%s,energy=%d)".format(dx, dy, name, energy)
}

case class Set(props: Map[String, Any]) extends PlayerCommand {
  override def toString = "Set(" + props.map(prop => prop._1 + "=" + prop._2).mkString(",") + ")"
}

case class Explode(size: Int) extends PlayerCommand {
  override def toString = "Explode(size=%s)".format(size)
}

case class Say(text: String) extends PlayerCommand {
  override def toString: String = "Say(text=%s)".format(text.replaceAll("|=,\\(", ""))
}

