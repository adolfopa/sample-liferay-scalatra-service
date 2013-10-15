package org.xardon.sample.services

import org.xardon.sample.models.User

trait UserServiceComponent {
  val userService: UserService

  trait UserService {
    def all(companyId: Long): Seq[User]
    def byId(companyId: Long, userId: Long): User
    def create(companyId: Long, user: User): User
    def delete(companyId: Long, userId: Long): Unit
  }
}