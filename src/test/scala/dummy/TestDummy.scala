package dummy
import org.scalatest.FunSpec
//import org.scalatest.matchers.ShouldMatchers

class TestDummy extends FunSpec {
  describe("Dummy") {
    it ("can instantiate") {
      val dummy = new Dummy
    }
  }
}
