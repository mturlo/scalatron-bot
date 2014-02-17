import command._

class ControlFunctionFactory {

  val commandParser: CommandParser = new SimpleCommandParser

  val commandSerializer: CommandSerializer = new SimpleCommandSerializer

  def create = (input: String) => {
    val playerCommands = Seq(Move(1, 1))
    commandSerializer.serialize(playerCommands)
  }

}
