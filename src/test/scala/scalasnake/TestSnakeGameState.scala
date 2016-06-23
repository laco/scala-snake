package scalasnake
import org.scalatest._

class TestSnakeGameState extends FunSpec with Matchers {
  val defaultSnake = List((2,0), (2,1), (2,2))

  describe("SnakeGameState") {
    it ("can create 5x5 snake game state with a snake") {
      val state = new SnakeGameState((5,5), defaultSnake)
      state.size should equal ((5, 5))
      state.snake should equal (defaultSnake)
    }

    it ("the minimum snake size is 2") {
      intercept[IllegalArgumentException] {
        new SnakeGameState((5,5), List((0,0)))
      }
    }

    it ("cannot create board with size 0x5") {
      intercept[IllegalArgumentException] {
        new SnakeGameState((0,5), defaultSnake)
      }
    }

    it ("cannot create board with size 5x0") {
      intercept[IllegalArgumentException] {
        new SnakeGameState((5,0), defaultSnake)
      }
    }

    it ("snake can be placed on the board") {
      intercept[IllegalArgumentException] {
        new SnakeGameState((4,4), List(
          (4,0), (4,1), (4,2)
        ))
      }
    }

    it ("snake with negative coords") {
      intercept[IllegalArgumentException] {
        new SnakeGameState((10,10), List(
          (0,0), (0,-1)
        ))
      }
    }

    it ("snake incountinuous") {
      intercept[IllegalArgumentException] {
        new SnakeGameState((5,5), List(
          (1,0), (1,1), (1,3), (1,4)
        ))
      }
    }

    it ("snake incountinuous2") {
      intercept[IllegalArgumentException] {
        new SnakeGameState((5,5), List(
          (1,0), (1,1), (2,2), (3,1)
        ))
      }
    }

    it ("snake with duplications") {
      intercept[IllegalArgumentException] {
        new SnakeGameState((5,5), List(
          (1,0), (1,1), (1,2), (1,1)
        ))
      }
    }

    it ("snake can move forward") {
      val state = new SnakeGameState((5,5), List(
          (3,3), (3,4)
      ))
      val nextState = state.step
      nextState.snake should equal(List((3,2), (3,3)))

      println(state.toString())
      println(nextState.toString())
    }

  }
}
