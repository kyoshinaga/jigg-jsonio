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
    
    //var source = Source.fromFile("./sample/sampleJpn.json")
    //val xml = XML.loadFile("./sample/sampleMini.xml")
    val jsonFile = "./sample/sampleMini.json"

    //JSONUtil.writeToJSON(xml,jsonFile)

    //println(JSONUtil.toJSON(xml))
    val json = JSONUtil.loadJSONFile(jsonFile)

    implicit val formats = DefaultFormats

    val jsonList = json.extract[Map[String, Any]].toList

    type childType = List[Map[String,String]]

    //val childMap = jsonList.apply(".child").asInstanceOf[childType]
    val childList = jsonList(1)._2.asInstanceOf[childType]


    def genXML(x: List[(String, Any)]): Node ={
      var tagString = ""
      var textString  = ""
      val attributeList = new ArrayBuffer[(String, String)]
      var emptyFlag = true

      for (i <- x){
        if (i._1 == ".tag")
          tagString = i._2.toString
        else if (i._1 == "text")
          textString = i._2.toString
        else if (i._1 == ".child"){
          emptyFlag = false
          val childNode = makeNode(i._2.asInstanceOf[childType]).toString
        }
        else
          attributeList += (i._1.toString -> i._2.toString)
      }

      val node = emptyFlag match{
        case true => <xml/>.copy(label = tagString)
        case false => <xml>{textString}</xml>.copy(label = tagString)
      }

      val fixnode = XMLUtil.addAttributes(node, attributeList.toMap)
      fixnode
      //XMLUtil.addChild(fixnode, childNode)
    }

    def genNode(x: Map[String, String]): Node = {
      var tagString = ""
      var textString  = ""
      val attributeList = new ArrayBuffer[(String, String)]
      var emptyFlag = true
      for((k,v) <- x){
        if(k == ".tag")
          tagString = v.toString
        else if(k == ".child")
          emptyFlag = false
        else if(k == "text")
          textString = v.toString
        else
          attributeList += (k.toString -> v.toString)
      }
      val node = emptyFlag match {
        case true => <xml/>.copy(label = tagString)
        case false => <xml>{textString}</xml>.copy(label = tagString)
      }
      val fixnode = XMLUtil.addAttributes(node, attributeList.toMap)
      fixnode
    }

    def makeNode(x: childType): NodeSeq = {
      scala.xml.NodeSeq.fromSeq(for {i <- x
           node <- genNode(i)
      } yield node)
    }

    println(genXML(jsonList))
  }
}
