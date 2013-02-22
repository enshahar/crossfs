package models

import scala.slick.driver.H2Driver.simple._
//import play.api.data._
//import play.api.data.Forms._

// user class
case class User(id: Option[Long] = None, loginname:String, name:String, mobile:String)

// user companion object
object Users extends Table[User]("users") {

  // primary key with auto increment
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  // unique loginname
  def loginname = column[String]("loginname", O.NotNull)

  def name = column[String]("name", O.NotNull)

  def mobile = column[String]("mobile", O.NotNull)

  // default projection
  def * = id.? ~ loginname ~ name ~ mobile <>(User, User.unapply _)

  def login_idx = index("idx_user_login", loginname, unique = true)

  /*def users: List[User] =
     List( User(1, "id1", "name1", "0100000001"),
           User(2, "id2", "name2", "0100000002"),
           User(3, "id3", "name3", "0100000003") )
  */
  val userForm = play.api.data.Form(
    play.api.data.Forms.mapping (
      "id" -> play.api.data.Forms.optional(play.api.data.Forms.longNumber),
      "loginname" -> play.api.data.Forms.nonEmptyText,
      "name" -> play.api.data.Forms.nonEmptyText,
      "mobile" -> play.api.data.Forms.text
    )(User.apply)(User.unapply)
  )

}