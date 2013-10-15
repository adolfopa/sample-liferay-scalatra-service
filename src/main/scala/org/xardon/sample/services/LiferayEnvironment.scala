package org.xardon.sample.services

trait LiferayEnvironment extends LiferayInstanceServiceComponent
	                        with LiferaySiteServiceComponent
	                        with LiferayUserServiceComponent
	                        with LiferayWebContentServiceComponent {
	
  val instanceService = new LiferayInstanceService
  val siteService = new LiferaySiteService
  val userService = new LiferayUserService
  val webContentService = new LiferayWebContentService
}