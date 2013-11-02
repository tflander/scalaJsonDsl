package toddf.json
import org.scalatest._
import ParseJson._

class JsonParserResultTest extends FunSpec with ShouldMatchers {

  describe("single element parsing") {

    it("parses empty Json") {
      val json = parseJson("{}")

      json.successful should be(true)
      println(json.get.toString)
      json.get should be (Map())
    }
    
    it("parses a numeric value") {
      val json = parseJson("""{"zip" : 48092}""")

      json.successful should be(true)
      println(json.get.toString) 
    }

    it("parses a string value") {
      
 /*
JsonMember = (("name"~:)~"todd") (scala.util.parsing.combinator.Parsers$$tilde)
JsonMembers = List(JsonMember((("name"~:)~"todd"))) (scala.collection.immutable.$colon$colon)
JsonObject = JsonMembers(List(JsonMember((("name"~:)~"todd")))) (toddf.json.JsonMembers)
JsonObject(JsonMembers(List(JsonMember((("name"~:)~"todd")))))
 */
      
      val json = parseJson("""{"name" : "todd"}""")

      json.successful should be(true)
      println(json.get.toString)
    }

    it("parses a null value") {
      val json = parseJson("""{"name" : null}""")

      json.successful should be(true)
      println(json.get.toString)
    }

    it("parses a boolean true value") {
      val json = parseJson("""{"cool" : true}""")

      json.successful should be(true)
      println(json.get.toString)
    }

    it("parses a boolean false value") {
      val json = parseJson("""{"cool" : false}""")

      json.successful should be(true)
      println(json.get.toString)
    }
  }
}