package org.xardon.sample.services

import com.liferay.portal.model.Company
import org.xardon.sample.models.Instance

trait InstanceServiceComponent {
	val instanceService: InstanceService
	
	trait InstanceService {
	  def all: Seq[Instance] 
	  def byId(id: Long): Instance
	}
}