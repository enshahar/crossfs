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

  // display a user : just call modify user with edit disable
  def user(id: Long) = modifyUser(id, false)

  // 사용자 폼을 표시한다.
  def newUser = Action { implicit request =>
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

  def deleteUser(id: Long) = Action { implicit request =>
    if(id >= 0) {
        if(Users.deleteUser(id) > 0)
          UsersHome.flashing("success" -> "User(id=%d) has been deleted".format(id))
        else
          UsersHome.flashing("error" -> "User(id=%d) delete error".format(id))
    } else
        UsersHome.flashing("error" -> "Invalid User ID")
  }


  def modifyUser(id: Long, editable:Boolean=true) = Action { implicit request =>
    if(id >= 0)
    {
        val u = Users.getUser(id)
        u match {
          case Some(v) => Ok(views.html.user.editForm(Users.userForm.fill(v), editable))
          case None => UsersHome.flashing("error" -> "Invalid User ID")
        }
    }
    else
        UsersHome.flashing("error" -> "Invalid User ID")
  }

  def updateUser(id: Long) = Action { implicit request =>
    Users.userForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.user.editForm(formWithErrors)),
      user => {
        user.id match {
          case Some(x) => 
            if(x==id) 
            {
              Users.updateUser(user)
              UsersHome.flashing("success" -> "User %s(%s) has been updated".format(user.loginname, user.name))
            }
            else 
              UsersHome.flashing("error" -> "User id %d is not match %d".format(x,id))
          case None => UsersHome.flashing("error" -> "null id error.")
        }        
      }
    )
  }
  
  def index = Action { implicit request =>
    Ok(views.html.index("Your new application is ready."))
  }
  
}
