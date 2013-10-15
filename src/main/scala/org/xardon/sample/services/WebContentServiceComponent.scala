package org.xardon.sample.services

import org.xardon.sample.models.WebContent

trait WebContentServiceComponent {
  val webContentService: WebContentService
  
  trait WebContentService {
    def all(companyId: Long, groupId: Long): Seq[WebContent]
    def byId(companyId: Long, groupId: Long, articleId: String): WebContent
  }
}