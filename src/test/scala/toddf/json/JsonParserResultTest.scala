package toddf.json
import org.scalatest._
import ParseJson._

class JsonParserResultTest extends FunSpec with ShouldMatchers {

  describe("single element parsing") {

    it("parses empty Json") {
      val json = parseJson("{}")

      json.successful should be(true)
      json.get should be(List())
    }

    it("parses a numeric value") {
      val json = parseJson("""{"zip" : 48092}""")

      json.successful should be(true)
      json.get should be(List(JsonLong("zip", 48092)))
    }

    it("parses a string value") {
      val json = parseJson("""{"name" : "todd"}""")

      json.successful should be(true)
      json.get should be(List(JsonString("name", "todd")))
    }

    it("parses a null value") {
      val json = parseJson("""{"name" : null}""")

      json.successful should be(true)
      json.get should be(List(JsonNull("name")))
    }

    it("parses a boolean true value") {
      val json = parseJson("""{"cool" : true}""")

      json.successful should be(true)
      json.get should be(List(JsonBoolean("cool", true)))
    }

    it("parses a boolean false value") {
      val json = parseJson("""{"cool" : false}""")

      json.successful should be(true)
      json.get should be(List(JsonBoolean("cool", false)))
    }
  }

  describe("Json Objects and Arrays") {

    it("parses a standard key-value pair object") {
      val json = parseJson("""
    	    {"address" : {"street" : "123 Main Street", 
    					  "city"   : "Springfield",
    					  "state"  : "California",
    					  "zip"    : 90210 
    					}
    		}
    	""")

      json.successful should be(true)
      json.get should be(List(
        JsonList("address",
          List(
            JsonString("street", "123 Main Street"),
            JsonString("city", "Springfield"),
            JsonString("state", "California"),
            JsonLong("zip", 90210)))))
    }

    it("parses an array") {
      val json = parseJson("""
    	    {"phone numbers" : [ 
    					  "555-1212",
    					  "655-8629"
    					]
    		}
    	""")

      json.successful should be(true)
      json.get should be(List(JsonList("phone numbers", List("555-1212", "655-8629"))))
    }

  }

  describe("Putting it all together") {
    it("parses all combinations of a Json object") {
      val json = parseJson("""
            {"address" : {"street" : "123 Main Street", 
    					  "city"   : "Springfield",
    					  "state"  : "California",
    					  "zip"    : "90210",
    		   			  "phone numbers" : [
    		   								 "555-1212",
    		   								 "655-1268"
    		   			  					]
    					}
    		}
           """)
      println(json)
      json.successful should be(true)
      json.get should be(List(
        JsonList("address", List(
          JsonString("street", "123 Main Street"),
          JsonString("city", "Springfield"),
          JsonString("state", "California"),
          JsonString("zip", "90210"),
          JsonList("phone numbers", List("555-1212", "655-1268"))))))
    }
  }
}