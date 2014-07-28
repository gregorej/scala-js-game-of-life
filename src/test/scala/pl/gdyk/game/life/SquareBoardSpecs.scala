package pl.gdyk.game.life

import org.specs2.mutable.Specification
import pl.gdyk.game.life.CellState._


class SquareBoardSpecs extends Specification {

  "SquareBoard" should {
    "return cell at given position" in {
      val board = new SquareBoard(2, 2)

      board.cellAt(Position(0, 0)) must beEqualTo(Cell(Position(0, 0), CellState.Dead))
    }

    "return update cell at given position" in {
      val board = new SquareBoard(2, 2)

      board.updateCell(Position(1, 0), CellState.Live)

      board.cellAt(Position(1, 0)) must beEqualTo(Cell(Position(1, 0), CellState.Live))
    }
  }

}
