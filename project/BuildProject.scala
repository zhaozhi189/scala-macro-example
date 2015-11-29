import sbt._
import Keys._


object BuildProject extends Build {

  import BuildSettings._
  import LibDepend._

  val unPublish = publishArtifact := false

  lazy val macros = Project("macros", file("macros"), settings =
    buildSettings ++ testFrameworkDepend ++ slickDepend ++ akkaDepend
  )

  lazy val macros_try = Project("macros_try", file("macros_try"), settings =
    buildSettings  ++ unPublish ++ slickDepend) dependsOn macros

  lazy val stackoverflow = Project("stackoverflow", file("stackoverflow"), settings =
    buildSettings ++ unPublish) dependsOn macros

  lazy val using = Project("macros_using", file("macros_using"), settings =
    buildSettings ++ unPublish ++ slickDepend) dependsOn macros dependsOn macros_try dependsOn stackoverflow

  lazy val `scala-macro-example` = Project("scala-macro-example", file("."), settings =
    buildSettings ++ unPublish).aggregate(macros, using, macros_try, stackoverflow)
}


//scalacOptions += "-Ymacro-debug-lite"
//
//scalacOptions += "-Xexperimental"