package org.xardon.sample.routes

import org.scalatra.ScalatraServlet
import org.xardon.sample.services.LiferayInstanceServiceComponent
import org.xardon.sample.LiferayScalatraServlet

trait InstanceRoutes {
  this: LiferayScalatraServlet with LiferayInstanceServiceComponent =>
  
  get("/instances") {
    instanceService.all
  }
  
  get("/instances/:companyId") {
   	instanceService byId params("companyId").toLong
  }
} 