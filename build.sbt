val scala3Version = "3.6.1"
lazy val startupTransition: State => State = "writeHooks" :: _

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-project",
    assembly / assemblyJarName := "scala-project.jar",
    scalaVersion := scala3Version,
    Global / onLoad := {
      val old = (Global / onLoad).value
      startupTransition compose old
    },
    jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report",
      None,
      JacocoThresholds(),
      Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML),
      "utf-8"
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.18" % Test,
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )
