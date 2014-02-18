package command

import view.{Position, View}
import scala.util.matching.Regex

trait ServerCommand

object CommandPatterns {

  private val reactStr: String = """React\(generation=(\d+),time=(\d+),view=([?_WMSmsPpBb]+),energy=(-?\d+),name=([\w-]+)(%s)?(%s)?(%s)?%s\)"""
  private val masterStr: String = """,master=\d+:\d+"""
  private val collisionStr: String = """,collision=\d:\d"""
  private val slavesStr: String = """,slaves=\d+"""
  private val stateStr: String = """,?(.*)"""


  val welcome = """Welcome\(name=(\w+),apocalypse=(\d+),round=(\d+),maxslaves=(\d+)\)""".r
  val goodbye = """Goodbye\(energy=(\d+)\)""".r
  val react: Regex = reactStr.format(masterStr, collisionStr, slavesStr, stateStr).r
  val master: Regex = masterStr.replaceAll( """\\d\+""", """(\\d+)""").r
  val collision: Regex = collisionStr.replaceAll( """\\d""", """(\\d)""").r
  val slaves: Regex = slavesStr.replaceAll( """\\d\+""", """(\\d+)""").r
  val state: Regex = stateStr.r

}

case class Welcome(name: String, apocalypse: Int, round: Int, maxSlaves: Int) extends ServerCommand

case class Goodbye(energy: Int) extends ServerCommand

case class React(
                  generation: Int,
                  time: Int,
                  view: View,
                  energy: Int,
                  name: String,
                  master: Option[Position],
                  collision: Option[Position],
                  slaves: Option[Int],
                  state: Option[Map[String, Any]]
                  ) extends ServerCommand