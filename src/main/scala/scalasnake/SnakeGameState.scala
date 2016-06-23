package scalasnake


class SnakeGameState(s: (Int, Int), sn: List[(Int, Int)]) {
  type Snake = List[(Int, Int)]
  type Coord = (Int, Int)

  require(isValidParams(s, sn), "Invalid params")

  val size = s
  val snake = sn

  def step () : SnakeGameState = {
    new SnakeGameState(size, moveSnake)
  }

  override def toString(): String = {
    var ret = ""
    for (i <- 0 to size._1) {
      var line = ""
      for (j <- 0 to size._2 ) {
        if (snake.contains((i,j))) line += '*'
        else line +='.'
      }
      ret +=  line + "\n"
    }
    ret
    }

  private def isValidParams(s: Coord, sn: Snake) = {
    def neightbouring(c1: Coord, c2: Coord):Boolean = {
      if (c1._1 == c2._1) (c1._2 - c2._2).abs == 1
      else if (c1._2 == c2._2) (c1._1 - c2._1).abs == 1
      else false
    }

    def isValidSnakeCoord(c: Coord): Boolean = {
      c._1 < s._1 && c._2 < s._2 && c._1 >= 0 && c._2 >= 0
    }

    def snakeIsValid (): Boolean = {
      sn.length >= 2 && sn.forall( c => isValidSnakeCoord(c)) &&
        (sn zip sn.tail).forall( z => neightbouring(z._1, z._2)) &&
      sn.toSet.size == sn.length
    }

    def boardIsValid (): Boolean = {
      s._1 > 0 && s._2 > 0
    }
    snakeIsValid && boardIsValid
  }

  private def moveSnake (): Snake = {
    val direction = (snake.head._1 - snake.tail.head._1, snake.head._2 - snake.tail.head._2)
    val newHead = (snake.head._1 + direction._1, snake.head._2 + direction._2)
    (newHead :: snake).dropRight(1)
  }
}
