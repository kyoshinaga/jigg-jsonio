package jiggJsonIO

import scala.xml._

import scala.collection.mutable.{StringBuilder, ArrayBuffer}
import scala.io.Source

import org.json4s.DefaultFormats
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

object Main {
  def main(args: Array[String]): Unit = {
    
    val jsonFile = "./sample/sample.json"
    
    val json = JSONUtil.loadJSONFile(jsonFile)

    val xml = JSONUtil.toXML(json)

    val printer = new scala.xml.PrettyPrinter(500, 2)

    println(printer.format(xml))

  }
}
