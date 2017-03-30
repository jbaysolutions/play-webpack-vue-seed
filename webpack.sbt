/*
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

stage <<= stage dependsOn webpack*/

/*
 * UI Build Scripts
 */

val Success = 0 // 0 exit code
val Error = 1 // 1 exit code

PlayKeys.playRunHooks <+= baseDirectory.map(Webpack.apply)

def runScript(script: String)(implicit dir: File): Int = Process(script, dir) !

def uiWasInstalled(implicit dir: File): Boolean = (dir / "node_modules").exists()

def runNpmInstall(implicit dir: File): Int =
  if (uiWasInstalled) Success else runScript(
    if(System.getProperty("os.name").toUpperCase().indexOf("WIN") >= 0) {
      "cmd /c npm install"
    } else {
      "npm install"
    }
  )

def ifUiInstalled(task: => Int)(implicit dir: File): Int =
  if (runNpmInstall == Success) task
  else Error

def runProdBuild(implicit dir: File): Int = ifUiInstalled(runScript(
  if(System.getProperty("os.name").toUpperCase().indexOf("WIN") >= 0) {
    "cmd /c npm run build"
  } else {
    "npm run build"
  }
))

//def runDevBuild(implicit dir: File): Int = ifUiInstalled(runScript("npm run build"))

//def runUiTests(implicit dir: File): Int = ifUiInstalled(runScript("npm run test-no-watch"))

/*
lazy val `ui-dev-build` = TaskKey[Unit]("Run UI build when developing the application.")

`ui-dev-build` := {
  implicit val UIroot = baseDirectory.value / "frontend"
  if (runDevBuild != Success) throw new Exception("Oops! UI Build crashed.")
}
*/

lazy val `ui-prod-build` = TaskKey[Unit]("Run UI build when packaging the application.")

`ui-prod-build` := {
  implicit val UIroot = baseDirectory.value / "frontend"
  if (runProdBuild != Success) throw new Exception("Oops! UI Build crashed.")
}

/*lazy val `ui-test` = TaskKey[Unit]("Run UI tests when testing application.")

`ui-test` := {
  implicit val UIroot = baseDirectory.value / "frontend"
  if (runUiTests != 0) throw new Exception("UI tests failed!")
}

`ui-test` <<= `ui-test` dependsOn `ui-dev-build`*/

dist <<= dist dependsOn `ui-prod-build`

stage <<= stage dependsOn `ui-prod-build`

//test <<= (test in Test) dependsOn `ui-test`
