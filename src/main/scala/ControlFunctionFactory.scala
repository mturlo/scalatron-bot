import ai.{BasicInstinctsAI, AI}
import command._

class ControlFunctionFactory {

  val commandParser: CommandParser = new SimpleCommandParser

  val commandSerializer: CommandSerializer = new SimpleCommandSerializer

  val ai: AI = new BasicInstinctsAI

  def create = (input: String) => {
    val serverCommands = commandParser.parse(input)
    val playerCommands = ai.reactTo(serverCommands)
    commandSerializer.serialize(playerCommands)
  }

}
