import org.scalatest.{FunSuite, Matchers}
import turorial.thriftscala.{TApple, TFruit, TOrange, TPerson}

/**
  * Created by Liam.M on 2018. 08. 27..
  */

sealed trait Fruit
object Fruit {
  case class Orange(a: Boolean) extends Fruit
  case class Apple(a: Int, b: String) extends Fruit
}

sealed trait Fruit1
object Fruit1 {
  case class Orange(a: Boolean) extends Fruit1
  case class Apple(a: Int, b: String) extends Fruit1
}
class ScroogeSpec extends FunSuite with Matchers {

  import io.scalaland.chimney.dsl._
  case class SPerson(tname: String, tid: Int, temail: String)
  /**
  test("scrooge") {
    val tperson = TPerson("TTT1", 10000, "tttt@mail")
//    tperson.temail
//    tperson.transformInto[SPerson]
    import scala.reflect.runtime.universe._
    weakTypeOf[TPerson].members.foreach(println)
    val tphone = weakTypeOf[TPerson]

    val companionApply = tphone.companion.decl(TermName("apply")).asMethod
    val params = companionApply.paramLists.head.map { param =>
      (param.asTerm.name, param.asTerm.info)
    }.toList

    val symbols = params.map(x => tphone.member(x._1))
    println(symbols)
  }
    */

  test("scrooge to case class") {
    val tperson = TPerson("TTT1", 10000, "tttt@mail")
    tperson.temail
    println(tperson.transformInto[SPerson])
  }

  test("case class to Thrift") {
    val sperson = SPerson("SSS1", 20000, "temail@mail")
    println(sperson.transformInto[TPerson])
  }
  /**


  test("coproduct to coproduct") {
    val fruit: Fruit = Fruit.Orange(true)
    println(fruit.transformInto[Fruit1])
  }
  */

  test("adt to union") {
    val tfruit: TFruit = TFruit.Apple(TApple(10, Some("hello")))
    val torange: TFruit = TFruit.Orange(TOrange(true))

    val fruit = tfruit.into[Fruit]
      .withCoproductInstance { (_: TFruit.UnknownUnionField) => Fruit.Orange(true)}
      .transform
    println(fruit)
  }
}
