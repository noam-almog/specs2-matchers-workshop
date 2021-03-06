package com.wix.spec2

import com.wix.spec2.PersonMatchers._
import com.wix.spec2.WorkshopMatchers._
import org.specs2.matcher.Matchers._
import org.specs2.matcher.{Matcher, Matchers}
import org.specs2.mutable.SpecWithJUnit

class AdaptWorkshopTest extends SpecWithJUnit {

  "Adapt matchers" should {

    "be able to match person who is middle aged" in {
      Person(age = 50) must beMiddleAged
    }

    "be able to match female person" in {
      Person(sex = "Female") must beFemale
      Person(sex = "Male") must not(beFemale)
    }

    "be able to match tall person" in {
      Person(height = 2.0d) must beTall
      Person(height = 1.3d) must not(beTall)
    }

    "be able to match sum of a list" in {
      Seq(30, 40, 30) must haveSumOf(100)
    }

    "be able to match average of a list" in {
      Seq(25, 25, 100) must haveAverageOf(50)
    }
  }

  "Adapt Matcher Function" should {

    "match a Workshop with some of the specified developers" in {
      Workshop( developers = Seq( Person(name = "dev1"), Person(name = "dev2") ) ) must haveDevelopers(developers = Person(name = "dev1"))
    }

    "compose matchers together" in {
      Some(Workshop( developers = Seq( Person(name = "dev1") ) )) must beSome( haveDevelopers(developers = Person(name = "dev1")) )
    }

    "be able to match a workshop room" in {
      Workshop(room = Some("room")) must beWorkshopWith(room = "room")
    }

    "be able to match workshop with some developers and no room" in {
      Workshop( developers = Seq( Person(name = "dev1"), Person(name = "dev2") ), room = Some("room") ) must beWorkshopWith(room = "room", developers = Person(name = "dev1"))
    }

    "be able to compose Seq[Person] matcher" in {
      Workshop( developers = Seq( Person(name = "dev1") ) ) must beWorkshopThat( contain(Person(name = "dev1")) )
    }
  }

  "Alter Matcher function" should {
    "person should be equal even if they have different names" in {
      val person =  Person(name = "dev1")

      person.copy(name = "another name") must beSameAs( person )
    }

    "compare workshops only by person excluding names" in {
      Workshop( developers = Seq( Person(name = "dev1"), Person(name = "dev2") ) ) must haveTheSameDevelopersAs( Person(name = "dev4"), Person(name = "dev3") )
    }
  }

}


object PersonMatchers {
  import Matchers._

  def beMiddleAged: Matcher[Person] = beBetween(40, 60) ^^ { (p: Person) => ??? }

  def beFemale: Matcher[Person] = beEqualTo("Female") ^^ { (p: Person) => ??? }

  def beTall: Matcher[Person] = be_>(1.80d) ^^ { (p: Person) => ??? }

  def haveSumOf(sum: Int): Matcher[Seq[Int]] = beEqualTo(sum) ^^ { (s: Seq[Int]) => ??? }
  
  def haveAverageOf(sum: Int): Matcher[Seq[Int]] = beEqualTo(sum) ^^ { (s: Seq[Int]) => ??? }

  def beSameAs(p: Person): Matcher[Person] =
    be_===( p ) ^^^ { (p: Person) => ??? }
}

object WorkshopMatchers {
  import Matchers._

  def haveDevelopers(developers: Person*): Matcher[Workshop] = (??? : Matcher[Seq[Person]]) ^^ { (_: Workshop).developers aka "developers" }

  def beWorkshopWith(room: String): Matcher[Workshop] = (??? : Matcher[Option[String]]) ^^ { (_: Workshop).room aka "room" }

  def beWorkshopWith(room: String, developers: Person*): Matcher[Workshop] = ???  // reuse previous matchers

  def beWorkshopThat(matches: Matcher[Seq[Person]]): Matcher[Workshop] = ???

  def haveTheSameDevelopersAs(developers: Person*): Matcher[Workshop] =
    contain(exactly( Seq[Matcher[Person]](???):_* )) ^^ { (_: Workshop).developers aka "developers"}
  // clue: developers.map( person => Matcher[Person] )
}


case class Person(age: Int = 50, sex: String = "Male", name: String = "Jimmie", height: Double = 1.90d)

case class Workshop(developers: Seq[Person] = Seq.empty, room: Option[String] = None)