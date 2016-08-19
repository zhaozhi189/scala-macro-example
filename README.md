# scala-macro-example
[![Build Status](https://travis-ci.org/1178615156/scala-macro-example.svg?branch=master)](https://travis-ci.org/1178615156/scala-macro-example)
```scala 
  lazy val `scala-macro-example` =
    ProjectRef( uri("git:https://github.com/1178615156/scala-macro-example"),"scala-macro-example")
```
#### conf parsing config path in complete 
```scala
import macross.conf.conf 
object global_conf {
  implicit val config: Config = ConfigFactory.load()
  @conf
  object hello {
    val name  = config.getString(conf.path /* == "hello.name" */)
    val world = config.getLong(conf.path/* == "hello.world" */).second.toMillis
    val ss    = conf.as[String]//config.getString("hello.ss")
    val ll    = conf.as[List[Int]] //config.getIntList("hello.ll")

  }

}
```

#### make play routes

it will auto make conf/routes file 

```scala
package controllers

import javax.inject.{Inject, Singleton}

import play.api._
import play.api.mvc._
import yjs.macrs.play.MakeRoute
import yjs.macrs.play.Routes._

@MakeRoute
@Path("/hello")
@Singleton
class Application @Inject()
() extends Controller {

  @Get("/index")
  @Get("/index2")
  def index(i:Int,s:String) = Action {
    Ok("1")
  }

  @Post("/hello")
  @Get("/xxx")
  def hello = Action(Ok("2"))

}
```

the conf/routes file it like this
```
GET     /hello/index   controllers.Application.index(i:Int,s:String)
GET     /hello/index2  controllers.Application.index(i:Int,s:String)
POST    /hello/hello   controllers.Application.hello
GET     /hello/xxx     controllers.Application.hello
```