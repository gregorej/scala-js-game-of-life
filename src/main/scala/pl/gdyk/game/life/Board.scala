package pl.gdyk.game.life

import scala.collection.mutable
import pl.gdyk.game.life.CellState._

trait Board extends Iterable[Cell] {

  def apply(position: Position) = cellAt(position)

  def cellAt(position: Position): Cell

  def neighboursOf(position: Position)(strategy: NeighbourhoodStrategy): Seq[Cell]
}

trait MutableBoard extends Board {
  def updateCell(position: Position, newState: CellState)
}

trait BoardFactory extends Function0[MutableBoard]

class SquareBoard(rows: Int, columns: Int) extends MutableBoard {

  private val cells: mutable.IndexedSeq[CellState] = mutable.ArraySeq.fill(rows * columns)(CellState.Dead)

  implicit private def positionToIndex(position: Position): Int = position.row * columns + position.column

  private def indexToPosition(index: Int): Position = Position(index / columns, index % columns)

  def cellAt(position: Position): Cell = Cell(position, cells(position))

  def updateCell(position: Position, newState: CellState.CellState): Unit = cells.update(position, newState)

  private def neighboursCandidates(position: Position): Seq[Position] = {

    def pos(row: Int, col: Int) = Position(row, col)

    val rowUp = (position.row  + rows - 1) % rows
    val rowDown = (position.row + 1) % rows
    val columnLeft = (position.column - 1 + columns) % columns
    val columnRight = (position.column + 1) % columns
    Seq(pos(rowUp, columnLeft), pos(rowUp, position.column), pos(rowUp, columnRight),
        pos(position.row, columnLeft), pos(position.row, columnRight),
        pos(rowDown, columnLeft), pos(rowDown, position.column), pos(rowDown, columnRight)
    )
  }

  def neighboursOf(position: Position)(strategy: NeighbourhoodStrategy): Seq[Cell] = {
    neighboursCandidates(position).filter(p => strategy(position, p)).map(cellAt)
  }

  private class CellIterator extends Iterator[Cell] {

    private var arrayIndex: Int = 0

    def hasNext: Boolean = arrayIndex < cells.length

    def next(): Cell = {
      val cell = Cell(indexToPosition(arrayIndex), cells(arrayIndex))
      arrayIndex = arrayIndex + 1
      cell
    }
  }

  def iterator: Iterator[Cell] = new CellIterator()
}
