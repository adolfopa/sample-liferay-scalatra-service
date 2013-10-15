package org.xardon.sample.services

import com.liferay.portal.service.UserLocalServiceUtil
import com.liferay.portal.kernel.dao.orm.QueryUtil
import scala.collection.JavaConverters._
import org.xardon.sample.models.User
import org.xardon.sample.models.Conversions._

trait LiferayUserServiceComponent extends UserServiceComponent {
  class LiferayUserService extends UserService {
    def all(companyId: Long): Seq[User] =
      UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS).asScala.toSeq map lrUserToUser
      
    def byId(companyId: Long, userId: Long): User =
      UserLocalServiceUtil.getUser(userId)
      
    def create(companyId: Long, u: User): User =
      UserLocalServiceUtil.addUser(
          u.creatorUserId, 
          companyId, 
          false, 
          u.password,
          u.password, 
          false, 
          u.screenName, 
          u.email, 
          0L,
          null,
          u.locale,
          u.firstName, 
          u.middleName, 
          u.lastName, 
          0, 
          0, 
          u.male, 
          u.birthDate.getDayOfMonth, 
          u.birthDate.getMonthOfYear, 
          u.birthDate.getYear, 
          u.jobTitle, 
          Array[Long](), 
          Array[Long](),
          Array[Long](),
          Array[Long](),
          true, 
          null
      )
    
    def delete(companyId: Long, userId: Long): Unit =
      UserLocalServiceUtil.deleteUser(userId)
  }
}