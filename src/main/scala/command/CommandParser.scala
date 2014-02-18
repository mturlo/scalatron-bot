package command

import view.{Position, SimpleViewParser, ViewParser}
import scala.util.matching.Regex


trait CommandParser {

  def parse(input: String): Seq[ServerCommand]

}

class SimpleCommandParser extends CommandParser {

  val viewParser: ViewParser = new SimpleViewParser

  import CommandPatterns._

  override def parse(input: String): Seq[ServerCommand] = {

    val commandStrings = input.split('|')

    commandStrings.map {

      case welcome(name, path, apocalypse, round) =>
        Welcome(name, path, apocalypse.toInt, round.toInt)

      case goodbye(energy) =>
        Goodbye(energy.toInt)

      case react(generation, time, view, energy, name, opt1, opt2, opt3, opt4) =>

        val optionalsMap = determineOptional(Seq(opt1, opt2, opt3, opt4))

        React(
          generation.toInt,
          time.toInt,
          viewParser.parse(view),
          energy.toInt,
          name,
          optionalsMap.getOrElse(master, None).asInstanceOf[Option[Position]],
          optionalsMap.getOrElse(collision, None).asInstanceOf[Option[Position]],
          optionalsMap.getOrElse(slaves, None).asInstanceOf[Option[Int]],
          optionalsMap.getOrElse(state, None).asInstanceOf[Option[Map[String, Any]]]
        )

    }.toSeq
  }

  def determineOptional(optionStrings: Seq[String]): Map[Regex, Option[Any]] = {
    optionStrings.map {
      case master(x, y) => master -> Some(x.toInt, y.toInt)
      case collision(x, y) => collision -> Some(x.toInt, y.toInt)
      case slaves(num) => slaves -> Some(num.toInt)
      case state(k) if !k.isEmpty =>
        val stateStrs = k.split(',').map {
          s =>
            val kv = s.split("=")
            kv(0) -> kv(1) //todo: parse value to its correct type according to key (now it's only strings)
        }.toMap
        state -> Some(stateStrs)
      case _ => "".r -> None
    }.toMap
  }

}