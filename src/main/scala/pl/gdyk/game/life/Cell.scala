package pl.gdyk.game.life


object CellState extends Enumeration {
  type CellState = Value
  val Live, Dead = Value
}

import CellState._
case class Cell(position: Position, state: CellState)
