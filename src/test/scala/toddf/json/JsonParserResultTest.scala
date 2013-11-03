package toddf.json
import org.scalatest._
import ParseJson._

class JsonParserResultTest extends FunSpec with ShouldMatchers {

  describe("resolves parsed values") {

    def resolveValue = parseJson(_)

    it("should resolve numbers") {
      val value = resolveValue("123")
      println(value)
      value.successful should be(true)
      value.get should be(123)
    }

    it("should resolve boolean true") {
      val value = resolveValue("true")
      println(value)
      value.successful should be(true)
      value.get.asInstanceOf[Boolean] should be(true)
    }

    it("should resolve boolean false") {
      val value = resolveValue("false")
      println(value)
      value.successful should be(true)
      value.get.asInstanceOf[Boolean] should be(false)
    }

    it("should resolve null") {
      val value = resolveValue("null")
      println(value)
      value.successful should be(true)
      value.get == null should be(true)
    }

    it("strips nulls from strings") {
      val value = resolveValue("\"hello\"")
      println(value)
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

    it("parses") {
      val nameValue = parseNameValue(""" "name": "value" """)
      println(nameValue)
      nameValue.successful should be(true)
      nameValue.get should be (List(JsonString("name", "value")))
    }
  }
}