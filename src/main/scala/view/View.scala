package view

case class View(locations: Seq[Location]) {
  def apply(x: Int, y: Int): Option[Location] = locations.find(loc => loc._1 == x && loc._2 == y)
}

abstract class Entity(val symbol: Char)

case object Unknown extends Entity('?')

case object Empty extends Entity('_')

case object Wall extends Entity('W')

case object Master extends Entity('M')

case object Slave extends Entity('S')

case object EnemyMaster extends Entity('m')

case object EnemySlave extends Entity('s')

case object Zugar extends Entity('P')

case object Toxifera extends Entity('p')

case object Fluppet extends Entity('B')

case object Snorg extends Entity('b')
