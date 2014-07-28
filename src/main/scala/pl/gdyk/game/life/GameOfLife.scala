package pl.gdyk.game.life

import org.scalajs.dom
import scala.scalajs.js
import org.scalajs.dom.CanvasRenderingContext2D
import pl.gdyk.game.life.ui.{RootPanel, GameController, JavascriptTaskExecutor, CanvasSquareGrid}


object GameOfLife extends js.JSApp {

  val taskExecutor: TaskExecutor = JavascriptTaskExecutor

  def stepListener(currentBoard: Board, nextBoard: Board): Unit = {
    dom.window.console.log("step finished")
    nextBoard.foreach(cell => null)
  }

  def intervalHandler(): Unit = {
    dom.window.console.log("interval")
  }

  @js.annotation.JSExport
  def main(): Unit = {
    val rootPanel = RootPanel("application")
    val grid = new CanvasSquareGrid(30,30)
    rootPanel.add(grid)

    val boardFactory = new BoardFactory {
      def apply(): MutableBoard = {
        val board = new SquareBoard(30, 30)
        Layouts.random(0.3)(board)
        board
      }
    }

    val controller = new GameController(boardFactory, grid, taskExecutor)
    controller.startNewGame()


  }
}
