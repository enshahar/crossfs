package controllers

import play.api._
import play.api.data._
import play.api.mvc._

import models._

object Application extends Controller {
  /**
   * This result directly redirect to the application home.
   */
  val UsersHome = Redirect(routes.Application.users)

  // User related methods
  def users = Action { implicit request =>
	  		Ok(views.html.user.users(Users.listUser))
	}

  def user(id: Long) = TODO
  
  // 사용자 폼을 표시한다.
  def newUser = Action {
    Ok(views.html.user.createForm(Users.userForm))
  }

  def saveUser = Action { implicit request =>
    Users.userForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.user.createForm(formWithErrors)),
      user => {
        Users.insertUser(user)
        UsersHome.flashing("success" -> "User %s(%s) has been created".format(user.loginname, user.name))
      }
    )
  }

  def deleteUser(id: Long) = TODO

  def modifyUser(id: Long) = TODO

  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
}
