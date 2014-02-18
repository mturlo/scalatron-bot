package view

import org.specs.Specification

class ViewParserSpec extends Specification {

  val parser = new SimpleViewParser

  "View Parser" should {

    "parse a simple view" in {

      val viewString =
        """
          |___
          |_MW
          |bpm
        """.stripMargin.replaceAll("\\n", "").trim

      val expectedView = View(
        Seq(
          (1, 1, Master), (2, 1, Wall),
          (0, 2, Snorg), (1, 2, Toxifera), (2, 2, EnemyMaster)
        )
      )

      parser.parse(viewString) must beEqualTo(expectedView)

    }

    "parse a complex view" in {

      import util.TestData._

      val viewString = complexViewStr

      val expectedView = complexView

      parser.parse(viewString) must beEqualTo(expectedView)

    }

  }

}
