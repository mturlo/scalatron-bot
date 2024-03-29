package command

import org.specs.Specification

class CommandParserSpec extends Specification {

  val parser = new SimpleCommandParser

  "Command parser" should {

    "parse Welcome command" in {

      val commandStr = "Welcome(name=my-bot,path=/some/path/to/my-bot,apocalypse=666,round=13)"

      val expectedCommand = Welcome("my-bot", "/some/path/to/my-bot", 666, 13)

      parser.parse(commandStr) must beEqualTo(Seq(expectedCommand))

    }

    "parse Goodbye command" in {

      val commandStr = "Goodbye(energy=100)"

      val expectedCommand = Goodbye(100)

      parser.parse(commandStr) must beEqualTo(Seq(expectedCommand))

    }

    "parse multiple commands in correct order" in {

      val welcomeCommandStr = "Welcome(name=my-bot,path=/some/path/to/my-bot,apocalypse=666,round=13)"
      val goodbyeCommandStr = "Goodbye(energy=100)"

      val combinedCommandStr = welcomeCommandStr + '|' + goodbyeCommandStr

      val expectedWelcomeCommand = Welcome("my-bot", "/some/path/to/my-bot", 666, 13)
      val expectedGoodbyeCommand = Goodbye(100)

      val expectedCommandSeq = Seq(expectedWelcomeCommand, expectedGoodbyeCommand)

      parser.parse(combinedCommandStr) must beEqualTo(expectedCommandSeq)

    }

    "parse react command with no optional values" in {

      import util.TestData._

      val viewString = complexViewStr

      val commandStr = "React(generation=0,time=1146,view=%s,energy=-3780,name=macior-bot)".format(viewString)

      val expectedCommand = React(0, 1146, complexView, -3780, "macior-bot", None, None, None, None)

      parser.parse(commandStr) must beEqualTo(Seq(expectedCommand))

    }

    "parse react command with some optional values" in {

      import util.TestData._

      val viewString = complexViewStr

      val commandStr = "React(generation=0,time=1146,view=%s,energy=-3780,name=macior-bot,master=7:8,slaves=4)".format(viewString)

      val expectedCommand = React(0, 1146, complexView, -3780, "macior-bot", Some(7, 8), None, Some(4), None)

      parser.parse(commandStr).head.asInstanceOf[React] must beEqualTo(expectedCommand)

    }

    "parse react command with all optional values" in {

      import util.TestData._

      val viewString = complexViewStr

      val commandStr = "React(generation=0,time=1146,view=%s,energy=-3780,name=macior-bot,master=7:8,collision=1:1,slaves=4,mykey1=myvalue1,mykey2=myvalue2)".format(viewString)

      val expectedState = Map("mykey1" -> "myvalue1", "mykey2" -> "myvalue2")
      val expectedCommand = React(0, 1146, complexView, -3780, "macior-bot", Some(7, 8), Some(1, 1), Some(4), Some(expectedState))

      parser.parse(commandStr).head.asInstanceOf[React].slaves must beEqualTo(expectedCommand.slaves)

    }

  }

}
