package org.xardon.sample.services

import org.xardon.sample.models.WebContent
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil

import scala.collection.JavaConverters._
import org.xardon.sample.models.Conversions._

trait LiferayWebContentServiceComponent extends WebContentServiceComponent {
  class LiferayWebContentService extends WebContentService {
    def all(companyId: Long, groupId: Long): Seq[WebContent] =
      JournalArticleLocalServiceUtil.getArticles(groupId).asScala.toSeq map lrJournalToWebContent
      
    def byId(companyId: Long, groupId: Long, articleId: String): WebContent =
      JournalArticleLocalServiceUtil.getArticle(groupId, articleId)
  }
}