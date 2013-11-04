package toddf.json
import org.scalatest._
import ParseJson._

class JsonParserResultTest extends FunSpec with ShouldMatchers {

  describe("resolves parsed values") {

    def resolveValue = parseJson(_)

    it("should resolve numbers") {
      val value = resolveValue("123")
      value.successful should be(true)
      value.get should be(123)
    }

    it("should resolve boolean true") {
      val value = resolveValue("true")
      value.successful should be(true)
      value.get.asInstanceOf[Boolean] should be(true)
    }

    it("should resolve boolean false") {
      val value = resolveValue("false")
      value.successful should be(true)
      value.get.asInstanceOf[Boolean] should be(false)
    }

    it("should resolve null") {
      val value = resolveValue("null")
      value.successful should be(true)
      value.get == null should be(true)
    }

    it("strips nulls from strings") {
      val value = resolveValue("\"hello\"")
      value.successful should be(true)
      value.get should be("hello")
    }

    it("fails non-Json Strings, where the string is not quoted") {
      val value = resolveValue("hello")
      value.successful should be(false)
    }
  }

  describe("name value parsing") {

    object NameValueParser extends Json {

      def parseNameValue(string: String) = {
        parseAll(member, string)
      }
    }

    import NameValueParser._

    it("parses to a JsonString") {
      val nameValue = parseNameValue(""" "name": "value" """)
      nameValue.successful should be(true)
      nameValue.get should be(JsonString("name", "value"))
    }

    it("parses to a JsonLong") {
      val nameValue = parseNameValue(""" "name": 123 """)
      nameValue.successful should be(true)
      nameValue.get should be(JsonLong("name", 123))
    }

    it("parses to a JsonNull") {
      val nameValue = parseNameValue(""" "name": null """)
      nameValue.successful should be(true)
      nameValue.get should be(JsonNull("name"))
    }

    it("parses to a true JsonBoolean") {
      val nameValue = parseNameValue(""" "name": true """)
      nameValue.successful should be(true)
      nameValue.get should be(JsonBoolean("name", true))
    }
    
    it("parses to a false JsonBoolean") {
      val nameValue = parseNameValue(""" "name": false """)
      nameValue.successful should be(true)
      nameValue.get should be(JsonBoolean("name", false))
    }
  }
  
  describe("get arrays working") {
      val jsonArray = parseJson(""" [false, true, null, "string", 123] """)
      jsonArray.successful should be(true)
      jsonArray.get should be(List(false, true, null, "string", 123))
  }
  
  describe("get objects to work") {
    
    it("handles a single element") {
      val jsonObject = parseJson(""" {"name": "value"} """)
      println(jsonObject)
      jsonObject.successful should be(true)
      jsonObject.get should be(List(JsonString("name", "value")))
    }
    
    it("handles multiple elements") {
      val jsonObject = parseJson(""" 
          {
    		  "name": "value",
    		  "name1": "value1"
          } 
          """)
      println(jsonObject)
      jsonObject.successful should be(true)
      jsonObject.get should be(List(JsonString("name", "value"), JsonString("name1", "value1")))      
    }
    
    it("handles complex objects") {
//      val json = parseJson("""
//            {"address" : {"street" : "123 Main Street", 
//                                              "city"   : "Springfield",
//                                              "state"  : "California",
//                                              "zip"    : "90210",
//                                                 "phone numbers" : [
//                                                                                        "555-1212",
//                                                                                        "655-1268"
//                                                                                         ]
//                                            }
//                    }
//           """)
//      println(json)
//      json.successful should be(true)
//      json.get should be(List(
//        JsonList("address", List(
//          JsonString("street", "123 Main Street"),
//          JsonString("city", "Springfield"),
//          JsonString("state", "California"),
//          JsonString("zip", "90210"),
//          JsonList("phone numbers", List("555-1212", "655-1268"))))))
    }
  }
}