import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import sbt._

object Webpack {
  def apply(base: File): PlayRunHook = {
    object WebpackHook extends PlayRunHook {
      var process: Option[Process] = None

      override def beforeStarted() = {
        if (!(base / "frontend" / "node_modules").exists())
          if(System.getProperty("os.name").toUpperCase().indexOf("WIN") >= 0) {
            Process("cmd /c npm install", base / "frontend").run
          } else {
            Process("npm install", base / "frontend").run
          }
      }

      override def afterStarted(addr: InetSocketAddress) = {
        process = Some(
          if(System.getProperty("os.name").toUpperCase().indexOf("WIN") >= 0) {
            Process("cmd /c node node_modules/webpack/bin/webpack.js --watch", base / "frontend").run
          } else {
            Process("node node_modules/webpack/bin/webpack.js --watch", base / "frontend").run
          }
        )

        /*process = Option(
          Process("webpack" + sys.props.get("os.name").filter(_.toLowerCase.contains("windows")).map(_ => ".cmd").getOrElse("") + " --watch", base).run()
//          Process("webpack-dev-server" + sys.props.get("os.name").filter(_.toLowerCase.contains("windows")).map(_ => ".cmd").getOrElse("") + " --inline --hot", base).run()
        )*/
      }

      override def afterStopped() = {
        process.foreach { proc =>
          println("STOPPING node process?")
          proc.destroy()
        }
        process = None
      }
    }

    WebpackHook
  }
}