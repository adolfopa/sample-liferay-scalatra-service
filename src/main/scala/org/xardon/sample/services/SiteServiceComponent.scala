package org.xardon.sample.services

import org.xardon.sample.models.Group

trait SiteServiceComponent {
  val siteService: SiteService
  
  trait SiteService {
    def all(companyId: Long): Seq[Group]    
    def byId(companyId: Long, groupId: Long): Group      
    def create(companyId: Long, g: Group): Group
    def delete(companyId: Long, groupId: Long): Unit
  }
}