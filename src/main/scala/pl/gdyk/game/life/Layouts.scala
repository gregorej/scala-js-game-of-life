package pl.gdyk.game.life

import scala.util.Random


object Layouts {

  private val random = new Random()

  def random(ratio: Double)(board: MutableBoard): Unit = {
    def calculateState() = if (random.nextDouble() < ratio) CellState.Live else CellState.Dead
    board.foreach(cell => board.updateCell(cell.position, calculateState()))
  }

}
