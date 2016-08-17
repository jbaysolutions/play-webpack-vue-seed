import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import sbt._

object Webpack {
  def apply(base: File): PlayRunHook = {
    object WebpackHook extends PlayRunHook {
      var process: Option[Process] = None

      override def beforeStarted() = {
        process = Option(
          Process("webpack" + sys.props.get("os.name").filter(_.toLowerCase.contains("windows")).map(_ => ".cmd").getOrElse(""), base).run()
        )
      }

      override def afterStarted(addr: InetSocketAddress) = {
//        afterStopped()
        process = Option(
          Process("webpack" + sys.props.get("os.name").filter(_.toLowerCase.contains("windows")).map(_ => ".cmd").getOrElse("") + " --watch", base).run()
//          Process("webpack-dev-server" + sys.props.get("os.name").filter(_.toLowerCase.contains("windows")).map(_ => ".cmd").getOrElse("") + " --inline --hot", base).run()
        )
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