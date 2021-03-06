import play.api.db.DB
import play.api.GlobalSettings
// Use H2Driver to connect to an H2 database
import scala.slick.driver.H2Driver.simple._
 
// Use the implicit threadLocalSession
import Database.threadLocalSession
 
import play.api.Application
import play.api.Play.current
 
import models._
 
object Global extends GlobalSettings {

  override def onStart(app: Application) {
    lazy val database = Database.forDataSource(DB.getDataSource())

    database.withSession {
     //Users.ddl.create
     val q = Query(Users)
     val stmt = q.selectStatement
     println(stmt)
    }
  }


}
