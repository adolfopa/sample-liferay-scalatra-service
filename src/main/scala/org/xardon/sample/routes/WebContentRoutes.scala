package org.xardon.sample.routes

import org.xardon.sample.LiferayScalatraServlet
import org.xardon.sample.services.WebContentServiceComponent

trait WebContentRoutes {
  this: LiferayScalatraServlet with WebContentServiceComponent =>
    
  get("/instances/:companyId/sites/:groupId/articles") {
    webContentService.all(params("companyId").toLong, params("groupId").toLong)
  }

  get("/instances/:companyId/sites/:groupId/articles/:articleId") {
    webContentService.byId(
        params("companyId").toLong,
        params("groupId").toLong,
        params("articleId")
    )
  }
}