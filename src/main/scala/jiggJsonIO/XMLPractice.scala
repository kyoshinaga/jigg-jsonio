package myPractice

import scala.io.Source
import scala.collection.mutable.{ ArrayBuffer, StringBuilder }
import scala.xml._

object XMLPractice {
  def main(args: Array[String]): Unit = {
    val filename: String = "../sampleData/annotated.xml"
    
    println(JSONUtil.toJSON(filename))
  }
}
