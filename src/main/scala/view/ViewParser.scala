package view


trait ViewParser {

  def parse(viewStr: String): View

}

class SimpleViewParser extends ViewParser {

  import scala.math._

  override def parse(viewStr: String): View = {

    val n = sqrt(viewStr.size).toInt

    var ind = -1

    val locations = viewStr.map {
      char => {
        ind += 1
        val i = ind
        val x = i % n
        val y = i / n
        val entity = char match {
          case Empty.symbol => Empty
          case Wall.symbol => Wall
          case Master.symbol => Master
          case Slave.symbol => Slave
          case EnemyMaster.symbol => EnemyMaster
          case EnemySlave.symbol => EnemySlave
          case Zugar.symbol => Zugar
          case Toxifera.symbol => Toxifera
          case Fluppet.symbol => Fluppet
          case Snorg.symbol => Snorg
          case Unknown.symbol => Unknown
        }
        (x, y, entity)
      }
    }.toSeq.filterNot(loc => loc._3 == Empty || loc._3 == Unknown)

    View(locations)
  }

}
