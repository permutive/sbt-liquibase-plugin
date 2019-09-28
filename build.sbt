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
  scalaVersion := "2.12.10",
  crossSbtVersions := Vector("0.13.16"),
  releaseCrossBuild := true,
  releaseTagName := {
    (version in ThisBuild).value
  },
  parallelExecution := true,

  sonatypeProfileName := "com.permutive",
  publishMavenStyle := true,
  licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  homepage := Some(url("https://github.com/permutive/sbt-liquibase-plugin")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/permutive/sbt-liquibase-plugin"),
      "scm:git@github.com:permutive/sbt-liquibase-plugin.git"
    )
  ),
  developers := List(
    Developer(id="paulius_permutive", name="Paulius Imbrasas", email="paulius@permutive.com", url=url("https://www.permutive.com")),
    Developer(id="lance", name="Lance Linder", email="", url=url("http://buddho.io/lance"))
  ),
  publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
  ),
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    releaseStepCommand("^publishSigned"),
    releaseStepCommand("sonatypeReleaseAll"),
    setNextVersion,
    commitNextVersion,
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
    libraryDependencies += "org.liquibase" % "liquibase-core" % "3.8.0"
  )
