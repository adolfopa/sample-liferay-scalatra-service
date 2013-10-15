package org.xardon.sample.routes

import org.scalatra.ScalatraServlet
import org.xardon.sample.services.LiferaySiteServiceComponent
import org.xardon.sample.LiferayScalatraServlet
import org.xardon.sample.models.Group

trait SiteRoutes {
  this: LiferayScalatraServlet with LiferaySiteServiceComponent =>
    
  get("/instances/:companyId/sites") {
   	siteService all params("companyId").toLong
  }
  
  get("/instances/:companyId/sites/:groupId") {
   	siteService.byId(params("companyId").toLong, params("groupId").toLong)
  }
  
  post("/instances/:companyId/sites") {
	siteService.create(params("companyId").toLong, parsedBody.extract[Group])
  }
  
  delete("/instances/:companyId/sites/:groupId") {
    siteService.delete(params("companyId").toLong, params("groupId").toLong)
  }
}