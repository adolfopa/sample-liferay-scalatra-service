package org.xardon.sample.services

import com.liferay.portal.service.CompanyLocalService
import com.liferay.portal.service.CompanyLocalServiceUtil

import scala.collection.JavaConverters._
import org.xardon.sample.models.Conversions._

trait LiferayInstanceServiceComponent extends InstanceServiceComponent {
	class LiferayInstanceService extends InstanceService {
	  def all =
	    CompanyLocalServiceUtil.getCompanies.asScala.toSeq map lrCompanyToInstance
	  
	  def byId(id: Long) =
	    CompanyLocalServiceUtil.getCompany(id)
	}
}