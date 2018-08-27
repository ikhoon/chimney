import org.scalatest.{FunSuite, Matchers}
import turorial.thriftscala.TPerson

/**
  * Created by Liam.M on 2018. 08. 27..
  */
class ScroogeSpec extends FunSuite with Matchers {

  import io.scalaland.chimney.dsl._
  case class SPerson(tname: String, tid: Int, temail: String)
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

  test("scrooge reflection") {
    val tperson = TPerson("TTT1", 10000, "tttt@mail")
    tperson.temail
    println(tperson.transformInto[SPerson])
  }

  test("case class to Thrift") {
    val sperson = SPerson("SSS1", 20000, "temail@mail")
    println(sperson.transformInto[TPerson])
  }
}
