package src.Strings

import scala.util.matching.Regex

import src.Strings.StringUtils._

object Main {

  def main(args: Array[String]): Unit = {
    println("Hello, world!")

    introduction()
    testingStringEquality()
    creatingMultilineStrings()
    splittingStrings()
    substitutingVariablesIntoStrings()
    processingAStringOneCharacterAtATime()
    findingPatternsInString()
    replacingPatternsInStrings()
    extractingPartsOfAStringThatMatchPatterns()
    accessingACharacterInString()
    addYourOwnMethodsToTheStringClass()
  }

  def introduction() = {
    println("Hello, world!".getClass.getName)

    val s = "Hello, world!"
    println(s.length)

    val s1 = s + "!!"
    println(s1)

    s.foreach(println)
    for (c <- s) println(c)

    s.getBytes.foreach(println)

    val s_no_l = s.filter(_ != 'l')
    println(s_no_l)

    val scala = "scala".drop(2).take(2).capitalize
    println(scala)
    println("scala".capitalize)
  }

  def testingStringEquality() = {
    val s1 = "Hello"
    val s2 = "Hello"
    val s3 = "H" + "ello"
    val s4: String = null;

    println(s1 == s2)
    println(s1 == s3)

    println(s3 == s4)
    println(s4 == s3)

    val s5 = "hello"
    println(s1.toUpperCase == s2.toUpperCase)

    val M = "Marisa"
    val m = "marisa"
    println(M.equalsIgnoreCase(m))

    val s6: String = null;
    // throws java.lang.NullPointerException
    // println(s4.equalsIgnoreCase(s6))
  }

  def creatingMultilineStrings() = {
    val foo = """This is
    #a "multiline"
    |'String'"""
    println(foo)
    println(foo.stripMargin)
    println(foo.stripMargin('#'))
    println(foo.stripMargin.stripMargin('#'))
    println(foo.stripMargin.stripMargin('#').replaceAll("\n", " "))
  }

  def splittingStrings() = {
    val s = "hello world"
    s.split(" ").foreach(println)
    s.split(' ').foreach(println)

    val l = "eggs, milk, butter, Coco Puffs"
    l.split(",").foreach(print)
    println()
    l.split(",").map(_.trim).foreach(print)
    println()

    val h = "hello world, this is All"
    h.split("\\s+").foreach(print)
  }

  def substitutingVariablesIntoStrings() = {
    val name = "Fred"
    val age = 33
    val weight = 200.00

    println(name.getClass())
    println(age.getClass())
    println(weight.getClass())

    println(s"$name is $age years old, and weights $weight pounds.")

    println(s"Age next year: ${age + 1}")
    println(s"You are 33 years old: ${age == 33}")

    val hannah = Student("Hannah", 95)
    println(hannah)
    println(s"${hannah.name} has a score of ${hannah.score}")
    println(s"$hannah.name has a score of $hannah.score")

    println(f"$name is $age years old, and weights $weight%.2f pounds.")
    println(f"$name is $age years old, and weights $weight%.0f pounds.")

    val out = f"$name, you weigh $weight%.0f pounds"
    println(out)

    val s = s"foo\nbar"
    val raw = raw"foo\nbar"
    println(s)
    println(raw)

    val s_old = "%s is %d years old".format(name, age)
    println(s_old)

    val person = new Person(name, age, weight)
    println(person)
  }
  
  class Person(name: String, age: Int, weight: Double) {
    override def toString: String =
      "%s, age %d, weight %.2f".format(name, age, weight)
  }

  case class Student(name: String, score: Int)

  def processingAStringOneCharacterAtATime() = {
    val upper = "hello, world".map(c => c.toUpper)
    println(upper)

    val upperShort = "hello, world".map(_.toUpper)
    println(upperShort)

    val upperNol = "hello, world".filter(_ != 'l').map(_.toUpper)
    println(upperNol)

    for (c <- "hello") println(c)

    val upperYield = for (c <- "hello, world") yield c.toUpper
    println(upperYield)

    val result = for {
      c <- "hello, world"
      if c!= 'l'
    } yield c.toUpper
    println(result)

    "hello".foreach(println)

    println("HELLO".map(c => (c.toByte + 32).toChar))
    println(
      "HELLO".map{ c =>
        (c.toByte + 32).toChar
      }
    )

    println("HELLO".map(toLowerMethod))
    println(for (c <- "HELLO") yield toLowerFuncion(c))

    "hello".getBytes().foreach(println)
  }

  // method
  def toLowerMethod(c: Char): Char = (c.toByte + 32).toChar
  // function
  val toLowerFuncion = (c: Char) => (c.toByte+32).toChar

  def findingPatternsInString() = {
    val numPattern = "[0-9]+".r
    println(numPattern)

    val address = "123 Main Street Suite 101"
    println(address)

    val match1 = numPattern.findFirstIn(address)
    println(match1)

    val matches = numPattern.findAllIn(address)
    matches.foreach(println)
    println(matches.toArray)

    val numPatternRegex = new Regex("['0-9]+")
    val match1Regex = numPatternRegex.findFirstIn(address)
    println(match1Regex)

    val noAddress = "No address given"
    val noMatch1 = numPattern.findFirstIn(noAddress)
    println(noMatch1)

    val result = numPattern.findFirstIn(noAddress).getOrElse("no match")
    println(result)

    match1 match {
      case Some(s) => println(s"Found: $s")
      case None =>
    }

    match1.foreach { e =>
      println(s"Found a match: $e")
    }
  }

  def replacingPatternsInStrings() = {
    val address = "123 Main Street".replaceAll("[0-9]", "x")
    println(address)

    val regex = "[0-9]".r
    val newAddress = regex.replaceAllIn("123 Main Street", "x")
    println(newAddress)

    val result = "123".replaceFirst("[0-9]", "x")
    println(result)

    val regexH = "H".r
    val resultH = regexH.replaceFirstIn("Hello, world", "J")
    println(resultH)
  }

  def extractingPartsOfAStringThatMatchPatterns() = {
    val pattern = "([0-9]+) ([A-Za-z]+)".r

    val pattern(count, fruit) = "100 Bananas"
    println(count)
    println(fruit)

    val MoviesZipRE = "movies (\\d{5})".r
    val MoviesNearCityStateRE = "movies near ([a-z]+), ([a-z]{2})".r

    val textUserTyped = "movies 80301"

    textUserTyped match {
      case MoviesZipRE(zip) => getSearchResultsZip(zip)
      case MoviesNearCityStateRE(city, state) => getSearchResultsNearCity(city, state)
      case _ => println("did not match a regex")
    }
  }

  val getSearchResultsZip = (zip: String) => println(zip)
  val getSearchResultsNearCity = (zip: String, state: String) => println(zip + state.toString())

  def accessingACharacterInString() = {
    // java
    println("hello".charAt(0))
    // scala
    println("hello"(0))
    println("hello"(1))
    // same as `println("hello"(1))`
    println("hello".apply(1))
  }

  def addYourOwnMethodsToTheStringClass() = {
    val result = "HAL".increment
    println(result)
    println(result.decrement)

    println(".|.".hideAll)

    println("4".plusOne)
    println("0".asBoolean)
    println("1".asBoolean)
  }
}
