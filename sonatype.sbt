// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "com.permutive"

// To sync with Maven central, you need to supply the following information:
publishMavenStyle := true

// License of your choice
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/permutive/sbt-liquibase-plugin"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/permutive/sbt-liquibase-plugin"),
    "scm:git@github.com:permutive/sbt-liquibase-plugin.git"
  )
)

developers := List(
  Developer(id="paulius_permutive", name="Paulius Imbrasas", email="paulius@permutive.com", url=url("https://www.permutive.com")),
  Developer(id="lance", name="Lance Linder", email="", url=url("http://buddho.io/lance"))
)

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)