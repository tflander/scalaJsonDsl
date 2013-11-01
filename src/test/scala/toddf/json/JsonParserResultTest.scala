package toddf.json
import org.scalatest._
import ParseJson._

class JsonParserResultTest extends FunSpec with ShouldMatchers {

  describe("single element parsing") {

    it("parses empty Json") {
      val json = parseJson("{}")

      json.successful should be(true)
      println(json.get.toString) // prints "(({~List())~})", but for now we are not going to worry about output
      json.get should be (Map())
    }
  }
}