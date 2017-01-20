package models

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.{AuthenticationException, UsernamePasswordToken}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import security.Password

/**
 *
 * @author wsargent
 * @since 1/8/12
 */

case class User(nickname: String, password: String)

object User {

  /**
   * Parse a User from a ResultSet
   */
  val simple = {
    get[String]("subject_data.nickname") ~ get[String]("subject_data.password") map { case nickname~password => User(nickname, password) }
  }

  def findByEmail(email: String): Option[User] = {
    DB.withConnection {
      implicit connection =>
        SQL("select * from user where email = {email}").on(
          'email -> email
        ).as(User.simple.singleOpt)
    }
  }

  def findAll: Seq[User] = {
    DB.withConnection {
      implicit connection =>
        SQL("select * from subject_data").as(User.simple *)
    }
  }

//  def attach(token:String) {
//    SecurityUtils.getSubject.login(token)
//  }

  def authenticate(email: String, password: String): Boolean = {
    // Use shiro to pass through a username password token.
    val token = new UsernamePasswordToken(email, password)
    //token.setRememberMe(true)

    val currentUser = SecurityUtils.getSubject
    try {
      currentUser.login(token)
      true
    } catch {
      case e: AuthenticationException => false
    }
  }

  def logout() {
    SecurityUtils.getSubject.logout()
  }


  def register(email: String, password: String): Boolean = {
    findByEmail(email) match {
      case None => {
        create(User(email, password))
        true
      }
      case _ => false
    }
  }

  def create(user: User): User = {
    DB.withConnection {
      implicit connection =>
        SQL(
          """
            insert into subject_data (nextval('id_seq')
              {nickname}, {password}
            )
          """
        ).on(
          'nickname -> user.nickname,
          'password -> Password.encryptPassword(user.password)
        ).executeUpdate()
        user
    }
  }


}
