package org.xardon.sample

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.xardon.sample.services.LiferayInstanceServiceComponent
import org.xardon.sample.routes.InstanceRoutes
import org.xardon.sample.routes.SiteRoutes
import scala.util.Try
import sun.org.mozilla.javascript.ErrorReporter
import org.xardon.sample.models.FailureResponse
import org.xardon.sample.models.FailureResponse
import javax.servlet.http.HttpServletRequest
import org.json4s.CustomSerializer
import com.liferay.portal.model.BaseModel
import com.liferay.portal.NoSuchGroupException
import com.liferay.portal.NoSuchCompanyException
import com.liferay.portlet.journal.NoSuchArticleException
import com.liferay.portal.NoSuchUserException
import org.json4s.ext.DateTimeSerializer

class LiferayScalatraServlet extends ScalatraServlet with JacksonJsonSupport {
  this: InstanceRoutes with SiteRoutes =>

  protected implicit val jsonFormats: Formats = new DefaultFormats {
    override def dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'")
  } + DateTimeSerializer

  def exnMessage(e: Throwable) = {
    def msg(t: Throwable): String =
      if (t == null) e.getClass.toString
      else if (t.getMessage == null) msg(t.getCause)
      else t.getMessage
      
    msg(e)
  }
  
  error {
    case e: NoSuchGroupException =>
      NotFound(FailureResponse("ERROR", exnMessage(e)))
    case e: NoSuchCompanyException =>
      NotFound(FailureResponse("ERROR", exnMessage(e)))
    case e: NoSuchArticleException =>
      NotFound(FailureResponse("ERROR", exnMessage(e)))
    case e: NoSuchUserException =>
      NotFound(FailureResponse("ERROR", exnMessage(e)))
    case e =>
      BadRequest(FailureResponse("ERROR", exnMessage(e)))
  }
      
  before("/*") {
    if (request.remoteAddress != "127.0.0.1")
      halt(403, "Sorry, no access.")

    contentType = formats("json")
  }
}
