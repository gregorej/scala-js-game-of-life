package pl.gdyk.game.life.ui

import org.scalajs.dom.{CanvasRenderingContext2D, HTMLCanvasElement, HTMLElement}
import org.scalajs.dom


trait SquareGrid extends UIObject {
  def updateColor(row: Int, column: Int, newColor: Color)
}

class CanvasWidget extends Widget {

  val element = dom.document.createElement("canvas").asInstanceOf[HTMLCanvasElement]

  private var parentWidget: Widget = _

  def parent: Widget = parentWidget

  def parent_=(parentWidget: Widget): Unit = {
    this.parentWidget = parentWidget
    element.width = parentWidget.element.clientWidth
    element.height = parentWidget.element.clientHeight
  }
}


class CanvasSquareGrid(rows: Int, columns: Int) extends CanvasWidget with SquareGrid {

  private val SQUARE_SIZE = 5
  private val context = element.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def updateColor(row: Int, column: Int, newColor: Color): Unit = {
    context.fillStyle = newColor
    context.fillRect(row * SQUARE_SIZE, column * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE)
  }
}
