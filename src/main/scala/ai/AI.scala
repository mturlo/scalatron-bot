package ai

import command.{PlayerCommand, ServerCommand}

trait AI {

  def reactTo(commands: Seq[ServerCommand]): Seq[PlayerCommand]

}

class BasicInstinctsAI extends AI {

  override def reactTo(commands: Seq[ServerCommand]): Seq[PlayerCommand] = Seq()

}
