package ai

import command.{Say, Welcome, PlayerCommand, ServerCommand}

trait AI {

  def reactTo(commands: Seq[ServerCommand]): Seq[PlayerCommand]

}

class BasicInstinctsAI extends AI {

  override def reactTo(commands: Seq[ServerCommand]): Seq[PlayerCommand] = commands.map {
    case Welcome(name, path, apocalypse, round) => {
      Say("ME %s. ME GET FOOD, ME AVOID BAD THINGIES.".format(name))
    }
  }

}
