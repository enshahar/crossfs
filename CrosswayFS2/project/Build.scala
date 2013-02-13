import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "CrosswayFS"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    //anorm
    "com.typesafe.slick" % "slick_2.10" % "1.0.0"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
