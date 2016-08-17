lazy val webpack = taskKey[Unit]("Run webpack when packaging the application")

def runWebpack(file: File) = {
  Process("webpack" + sys.props.get("os.name").filter(_.toLowerCase.contains("windows")).map(_ => ".cmd").getOrElse(""),
    file,
    "NODE_ENV" -> "production"
  ) !
}

webpack := {
  if(runWebpack(baseDirectory.value) != 0) throw new Exception("Something goes wrong when running webpack.")
}

dist <<= dist dependsOn webpack

stage <<= stage dependsOn webpack