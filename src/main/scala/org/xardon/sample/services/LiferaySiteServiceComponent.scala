package org.xardon.sample.services

import com.liferay.portal.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.dao.orm.QueryUtil
import scala.collection.JavaConverters._
import org.xardon.sample.models.Group

import org.xardon.sample.models.Conversions._

trait LiferaySiteServiceComponent extends SiteServiceComponent {
  class LiferaySiteService extends SiteService {
    def all(companyId: Long): Seq[Group] = {
      val sites = GroupLocalServiceUtil.getCompanyGroups(
        companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS).asScala.toSeq
      sites map lrGroupToGroup
    }

    def byId(companyId: Long, groupId: Long) =
      GroupLocalServiceUtil.getGroup(groupId)

    def create(companyId: Long, g: Group): Group = {
      GroupLocalServiceUtil.addGroup(
        g.userId,
        g.className,
        g.classPK,
        g.liveGroupId,
        g.name,
        g.description,
        g.groupType,
        g.friendlyURL,
        g.site,
        g.active,
        null)
    }
    
    def delete(companyId: Long, groupId: Long) =
      GroupLocalServiceUtil.deleteGroup(groupId)
  }
}