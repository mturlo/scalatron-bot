package command

import org.specs.Specification
import view.View

class CommandParserSpec extends Specification {

  val parser = new SimpleCommandParser

  val viewString =
    """
      |__________________??????????___
      |__________________?????????____
      |__________________????????_____
      |_________________?????????_____
      |_________________????????_____B
      |_________________???????______?
      |_________________WWWWWW_____???
      |_____p_____________________????
      |__________________________W????
      |__________________________W????
      |__________________________W????
      |___________b______________W????
      |__________________________W????
      |__________________________W????
      |__________________________W????
      |_______________M_______________
      |________________W?_____________
      |________________W???___________
      |___________?WW__W?????_________
      |__________??WW__W???????_______
      |_________???WW__W?????????WWWWW
      |p_______????WW__???????????????
      |___?W_p?????WW__???????????????
      |_???W_??????WW__???????????????
      |???WW???????WW__???????????????
      |????????????WW__???????????????
      |????????????WW__???????????????
      |????????????WW___??????????????
      |????????????WW___??????????????
      |????????????WW___??????????????
      |????????????WW___??????????????
    """.stripMargin.replaceAll("\\n", "").trim

  "Command parser" should {

    "parse Welcome command" in {

      val commandStr = "Welcome(name=bot,apocalypse=666,round=13,maxslaves=10)"

      val expectedCommand = Welcome("bot", 666, 13, 10)

      parser.parse(commandStr) must beEqualTo(Seq(expectedCommand))

    }

    "parse Goodbye command" in {

      val commandStr = "Goodbye(energy=100)"

      val expectedCommand = Goodbye(100)

      parser.parse(commandStr) must beEqualTo(Seq(expectedCommand))

    }

    "parse multiple commands in correct order" in {

      val welcomeCommandStr = "Welcome(name=bot,apocalypse=666,round=13,maxslaves=10)"
      val goodbyeCommandStr = "Goodbye(energy=100)"

      val combinedCommandStr = welcomeCommandStr + '|' + goodbyeCommandStr

      val expectedWelcomeCommand = Welcome("bot", 666, 13, 10)
      val expectedGoodbyeCommand = Goodbye(100)

      val expectedCommandSeq = Seq(expectedWelcomeCommand, expectedGoodbyeCommand)

      parser.parse(combinedCommandStr) must beEqualTo(expectedCommandSeq)

    }

    "parse react command with no optional values" in {

      val commandStr = "React(generation=0,time=1146,view=%s,energy=-3780,name=macior-bot)".format(viewString)

      val expectedCommand = React(0, 1146, View(Seq()), -3780, "macior-bot", None, None, None, None)

      parser.parse(commandStr) must beEqualTo(Seq(expectedCommand))

    }

    "parse react command with some optional values" in {

      val commandStr = "React(generation=0,time=1146,view=%s,energy=-3780,name=macior-bot,master=7:8,slaves=4)".format(viewString)

      val expectedCommand = React(0, 1146, View(Seq()), -3780, "macior-bot", Some(Position(7, 8)), None, Some(4), None)

      parser.parse(commandStr).head.asInstanceOf[React] must beEqualTo(expectedCommand)

    }

    "parse react command with all optional values" in {

      val commandStr = "React(generation=0,time=1146,view=%s,energy=-3780,name=macior-bot,master=7:8,collision=1:1,slaves=4,mykey1=myvalue1,mykey2=myvalue2)".format(viewString)

      val expectedState = Map("mykey1" -> "myvalue1", "mykey2" -> "myvalue2")
      val expectedCommand = React(0, 1146, View(Seq()), -3780, "macior-bot", Some(Position(7, 8)), Some(Position(1, 1)), Some(4), Some(expectedState))

      parser.parse(commandStr).head.asInstanceOf[React].slaves must beEqualTo(expectedCommand.slaves)

    }

  }

}
