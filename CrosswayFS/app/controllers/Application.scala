package controllers

import play.api._
import play.api.mvc._

import play.api.Play.current

import play.api.data._
import play.api.db.DB
import scala.slick.driver.H2Driver.simple._

// let's user thread local session
import Database.threadLocalSession

object Application extends Controller {

  lazy val database = Database.forDataSource(DB.getDataSource())

  // User related methods
  def users = Action {
    Ok(views.html.user.users(
      database withSession {
        Query(models.Users).list
      }
    ))
  }

  def user(id: Long) = TODO
  
  def newUser = TODO

  def deleteUser(id: Long) = TODO

  def modifyUser(id: Long) = TODO

  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
}
