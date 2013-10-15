import org.xardon.sample._
import org.scalatra._
import javax.servlet.ServletContext
import org.xardon.sample.services.LiferayEnvironment
import org.xardon.sample.routes.InstanceRoutes
import org.xardon.sample.routes.SiteRoutes
import org.xardon.sample.routes.UserRoutes
import org.xardon.sample.routes.WebContentRoutes

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new LiferayScalatraServlet
    				with InstanceRoutes
    				with SiteRoutes
    				with UserRoutes
    				with WebContentRoutes
    				with LiferayEnvironment, "/*")
  }
}
