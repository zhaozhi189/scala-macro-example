package yjs.macrs.slick

/**
  * Created by yujieshui on 2017/5/12.
  */
case class Entity(id: Int, name: String, helloWorld: Option[String])

import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

case class EntityTable(tag: Tag) extends Table[Entity](tag, "entity") {
  val id           = column[Int]("id", O.PrimaryKey)
  val name         = column[String]("name")
  val passwordHash = column[Option[String]]("helloWorld")

  override def * : ProvenShape[Entity] = (id, name, passwordHash) <> (Entity.tupled, Entity.unapply)
}
