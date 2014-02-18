package view

case class View(locations: Seq[Location]) {
  def apply(x: Int, y: Int): Option[Location] = locations.find(loc => loc._1 == x && loc._2 == y)
}

sealed trait Nutritious {
  def nutritionAmount: Int
}

sealed trait Harmful {
  def damageAmount: Int
}

sealed trait Mobile

abstract class Entity(val symbol: Char)

case object Unknown extends Entity('?')

case object Empty extends Entity('_')

case object Wall extends Entity('W') with Harmful {
  override def damageAmount: Int = 10
}

case object Master extends Entity('M')

case object Slave extends Entity('S')

case object EnemyMaster extends Entity('m')

case object EnemySlave extends Entity('s')

case object Zugar extends Entity('P') with Nutritious {
  override def nutritionAmount: Int = 100
}

case object Toxifera extends Entity('p') with Harmful {
  override def damageAmount: Int = 100
}

case object Fluppet extends Entity('B') with Nutritious with Mobile {
  override def nutritionAmount: Int = 200
}

case object Snorg extends Entity('b') with Harmful with Mobile {
  override def damageAmount: Int = 150
}
