package controllers

import play.api.db.DB
import play.api.GlobalSettings

// Use H2Driver to connect to an H2 database
import scala.slick.driver.H2Driver.simple._

import Database.threadLocalSession

import play.api.Play.current
import play.api._
import play.api.mvc._

import models._

object Application extends Controller {

  // User related methods
  def users = Action {
  	Database.forDataSource(DB.getDataSource()) withSession {
  		{
	  		Ok(views.html.user.users(Query(Users).list))
	  	}
  	}
	}

  def user(id: Long) = TODO
  
  // 사용자 폼을 표시한다.
  def newUser = TODO

  def saveUser = TODO

  def deleteUser(id: Long) = TODO

  def modifyUser(id: Long) = TODO

  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
}
