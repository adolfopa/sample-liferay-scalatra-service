package org.xardon.sample.models

import com.liferay.portal.model.Company
import com.liferay.portlet.journal.model.JournalArticle
import com.liferay.portlet.journal.model.JournalStructure
import org.joda.time.DateTime
import java.util.Locale

object Conversions {
  implicit def lrGroupToGroup(g: com.liferay.portal.model.Group) =
    Group(
        g.getCreatorUserId,
        g.getClassName,
        g.getClassPK,
        g.getLiveGroupId,
        g.getName,
        g.getDescription,
        g.getType,
        g.getFriendlyURL,
        g.getSite,
        g.getActive
    )
    
  implicit def lrCompanyToInstance(c: Company) =
    Instance(c.getCompanyId, c.getMx)
    
  implicit def lrJournalToWebContent(j: JournalArticle) =
    WebContent(
        j.getUserId,
        j.getClassName,
        j.getClassPK,
        j.getId,
        j.getArticleId,
        j.getVersion,
        j.getTitle,
        j.getDescription,
        j.getContent,
        j.getTemplateId,
        j.getStructureId
    )
    
    implicit def lrStructureToStructure(j: JournalStructure) =
      Structure(
          j.getUserId,
          j.getId,
          j.getStructureId,
          j.getName,
          j.getDescription,
          j.getXsd
    )
    
    implicit def lrUserToUser(u: com.liferay.portal.model.User) =
      User(
          u.getUserId,
          0,
          u.getPassword,
          u.getScreenName,
          u.getEmailAddress,
          u.getLocale,
          u.getFirstName,
          u.getMiddleName,
          u.getLastName,
          u.isMale,
          new DateTime(u.getBirthday()),
          u.getJobTitle
      )
}

case class FailureResponse[T](code: String, message: String)

case class Instance(companyId: Long,
                    mx: String)
                    
case class Group(userId: Long,
				 className: String,
				 classPK: Long,
				 liveGroupId: Long,
				 name: String,
				 description: String,
				 groupType: Int,
				 friendlyURL: String,
				 site: Boolean,
				 active: Boolean)
				 
case class WebContent(userId: Long,
					  className: String,
					  classPK: Long,
					  id: Long,
					  articleId: String,
					  version: Double,
					  title: String,
					  description: String,
					  content: String,
					  templateId: String,
					  structureId: String)
					  
case class Structure(userId: Long,
					 id: Long,
					 structureId: String,
					 name: String,
					 description: String,
					 xsd: String)
					 
case class User(userId: Long,
				creatorUserId: Long,
				password: String,
				screenName: String,
				email: String,
				locale: Locale,
				firstName: String,
				middleName: String,
				lastName: String,
				male: Boolean,
				birthDate: DateTime,
				jobTitle: String)		  

	 