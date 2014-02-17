package view

import command.Position

case class View(locations: Seq[Location])

abstract class Entity(symbol: Char)

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

case class Location(position: Position, entity: Entity)
