package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  // User related methods
  def users = Action {
    Ok(views.html.user.users(models.User.users))
  }

  def user(id: Long) = TODO
  
  def newUser = TODO

  def deleteUser(id: Long) = TODO

  def modifyUser(id: Long) = TODO

  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
}
