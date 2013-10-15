package org.xardon.sample.routes

import org.json4s.jvalue2extractable
import org.xardon.sample.LiferayScalatraServlet
import org.xardon.sample.models.User
import org.xardon.sample.services.UserServiceComponent

trait UserRoutes {
  this: LiferayScalatraServlet with UserServiceComponent =>
    
  get("/instances/:companyId/users") {
    userService all params("companyId").toLong
  }
  
  get("/instances/:companyId/users/:userId") {
    userService.byId(params("companyId").toLong, params("userId").toLong)
  }
  
  post("/instances/:companyId/users") {
    userService.create(params("companyId").toLong, parsedBody.extract[User])
  }
  
  delete("/instances/:companyId/users/:userId") {
    userService.delete(params("companyId").toLong, params("userId").toLong)
  }
}