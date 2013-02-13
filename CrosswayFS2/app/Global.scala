import play.api.db.DB
import play.api.GlobalSettings
// Use H2Driver to connect to an H2 database
import scala.slick.driver.H2Driver.simple._

// Use the implicit threadLocalSession
import Database.threadLocalSession

import play.api.Application
import play.api.Play.current

import models.Users
import models.User

object Global extends GlobalSettings {

  override def onStart(app: Application) {

		println("onStart(app: Application)")
		
    lazy val database = Database.forDataSource(DB.getDataSource())

		database withSession {
			Users.ddl.create
		
			Users.insert(User(Some(1), "id1", "name1", "0100000001"))
			Users.insert(User(Some(2), "id2", "name2", "0100000002"))
			Users.insert(User(Some(3), "id3", "name3", "0100000003"))
			Users.insert(User(None, "id4", "name4", "0100000004"))
			Users.insert(User(None, "id5", "name5", "0100000005"))
		}
  }
}