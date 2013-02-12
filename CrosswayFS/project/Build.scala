import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "CrosswayFS"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    //anorm,
    "com.typesafe" % "slick_2.10.0-M7" % "0.11.1"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
