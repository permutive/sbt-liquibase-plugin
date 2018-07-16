import ReleaseTransformations._

lazy val buildSettings = Seq(
  organization := "com.permutive",
  organizationName := "Permutive Inc.",
  organizationHomepage := Some(new URL("https://permutive.com")),
  publishArtifact in Test := false,
  sbtPlugin := true,
  scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked"),
  scriptedBufferLog := false,
  scriptedLaunchOpts := {
    scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
  },
  scalaVersion := "2.12.4",
  crossSbtVersions := Vector("1.0.4", "0.13.16"),
  releaseCrossBuild := true,
  releaseTagName := {
    (version in ThisBuild).value
  },
  parallelExecution := true,
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    releaseStepCommand("^publishSigned"),
    setNextVersion,
    commitNextVersion,
    releaseStepCommand("sonatypeReleaseAll"),
    pushChanges
  ),

)

lazy val sbtLiquibase = Project(
  id = "sbt-liquibase",
  base = file(".")
)
  .enablePlugins(ScriptedPlugin)
  .settings(buildSettings)
  .settings(
    libraryDependencies += "org.liquibase" % "liquibase-core" % "3.6.1"
  )
