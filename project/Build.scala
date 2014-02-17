import sbt._
import Keys._

// for more details, see http://jmhofer.johoop.de/?p=292
// put your Scalatron.jar into lib/

object Build extends Build {
  val botDirectory = SettingKey[File]("bot-directory")
  val play = TaskKey[Unit]("play")

  val botSettings = Seq[Setting[_]](
    organization := "macior",
    name := "macior-bot",
    version := "1.0.0-SNAPSHOT",

    scalaVersion := "2.9.3",
    scalacOptions ++= Seq("-deprecation", "-unchecked"),

    javaOptions += "-Xmx1g",

    libraryDependencies ++= Seq(),

    botDirectory := file("bots"),

    play <<= (botDirectory, name, javaOptions, unmanagedClasspath in Compile, Keys.`package` in Compile) map {
      (bots, name, javaOptions, ucp, botJar) =>
        IO createDirectory (bots / name)
        IO copyFile(botJar, bots / name / "ScalatronBot.jar")

        val cmd = "java %s -cp %s scalatron.main.Main scalatron-options botwar-options -plugins %s -browser no -port 8081" format(
          javaOptions mkString " ",
          Seq(ucp.files.head, botJar).absString,
          bots.absolutePath)
        cmd.run()
    }
  )

  val bot = Project(
    id = "maciorbot",
    base = file("."),
    settings = Project.defaultSettings ++ botSettings)
}