package pl.gdyk.game.life.ui

import pl.gdyk.game.life._
import pl.gdyk.game.life.CellState._


class GameController(boardFactory: BoardFactory, visualizer: SquareGrid, taskExecutor: TaskExecutor) {

  private var currentGame: Game = null

  private var gameTask: Task = null

  private def color(cell: Cell): ui.Color = cell.state match {
    case Live => "#000000"
    case Dead => "#FFFFFF"
  }

  private def updateVisualization(currentBoard: Board, newBoard: Board): Unit = {
    currentBoard.foreach(cell => visualizer.updateColor(cell.position.row, cell.position.column, color(cell)))
  }

  def startNewGame(): Unit = {
    pause()
    currentGame = new Game(boardFactory)
    currentGame.stepListeners += updateVisualization
    unpause()
  }

  def pause(): Unit = {
    if (gameTask != null) {
      gameTask.cancel()
      gameTask = null
    }
  }

  def unpause(): Unit = {
    if (gameTask == null) {
      gameTask = taskExecutor.execute(currentGame.nextStep, 1)
    }
  }


}
