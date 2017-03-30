import play.sbt.PlayImport.PlayKeys._

name := """play-webpack-vue-seed"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

doc in Compile <<= target.map(_ / "none")

//playRunHooks <+= baseDirectory.map(Webpack.apply)

pipelineStages := Seq(digest, gzip)

DigestKeys.algorithms += "sha1"
