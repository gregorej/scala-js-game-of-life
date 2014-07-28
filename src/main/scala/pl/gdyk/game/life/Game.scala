package pl.gdyk.game.life

import pl.gdyk.game.life.CellState._

case class Position(row:Int, column: Int)



trait NeighbourhoodStrategy extends Function2[Position, Position, Boolean]

object VonNeumannNeighbourhood extends NeighbourhoodStrategy {
  override def apply(position1: Position, position2: Position): Boolean = {
    (position1.row == position2.row && Math.abs(position1.column - position2.column) == 1) ||
    (position1.column == position2.column && Math.abs(position1.row - position2.row) == 1)
  }
}

class Game(boardFactory: BoardFactory) {

  var currentBoard = boardFactory()
  var nextBoard = boardFactory()

  val cellStateListeners: scala.collection.mutable.ListBuffer[Function1[Cell, Unit]] = scala.collection.mutable.ListBuffer()
  val stepListeners: scala.collection.mutable.ListBuffer[Function2[Board, Board, Unit]] = scala.collection.mutable.ListBuffer()

  private def calculateNewState(cell: Cell): CellState = {
    val liveNeighbourCount = currentBoard.neighboursOf(cell.position)(VonNeumannNeighbourhood).count(neighbour => neighbour.state == Live)
    cell.state match {
      case Live => if (liveNeighbourCount == 2 || liveNeighbourCount == 3)  Live else Dead
      case Dead => if (liveNeighbourCount == 3) Live else Dead
    }
  }

  private def processCell(cell: Cell): Unit = {
    val newState = calculateNewState(cell)
    if (newState != cell.state) {
      cellStateListeners.foreach(l => l(cell))
    }
    nextBoard.updateCell(cell.position, newState)
  }

  def nextStep(): Unit = {
    currentBoard.foreach(processCell)
    stepListeners.foreach(l => l(currentBoard, nextBoard))
    val tempBoard = currentBoard
    currentBoard = nextBoard
    nextBoard = tempBoard
  }

}
