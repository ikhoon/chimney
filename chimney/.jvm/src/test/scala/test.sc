import turorial.thriftscala.TPerson

import scala.reflect.runtime.universe._

val tphone = weakTypeOf[TPerson]
tphone.members
val companionApply = tphone.companion.decl(TermName("apply")).asMethod

tphone.decls
companionApply.paramLists.head.map { param =>
  (param.asTerm.name, param.asTerm.info)
}.toList


trait ABC
val abc = weakTypeOf[ABC]
val abcCompanionApply = abc.companion.decl(TermName("apply")).isMethod


