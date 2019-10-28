organization := "com.gojek.parking"

name := "parking-lot"

version := "1.0.0"

scalaVersion := "2.12.0"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

assemblyOutputPath in assembly := baseDirectory.value / "distribution" / s"${name.value}-assembly-${version.value}.jar"
