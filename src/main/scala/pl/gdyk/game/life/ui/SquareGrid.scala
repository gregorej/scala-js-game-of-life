package pl.gdyk.game.life.ui

import org.scalajs.dom.{CanvasRenderingContext2D, HTMLCanvasElement, HTMLElement}
import org.scalajs.dom


trait SquareGrid extends UIObject {
  def updateColor(row: Int, column: Int, newColor: Color)
}

class CanvasWidget(initWidth: Option[Int], initHeight: Option[Int]) extends Widget {

  def this() = this(None, None)
  def this(width: Int, height: Int) = this(Some(width), Some(height))

  val element = dom.document.createElement("canvas").asInstanceOf[HTMLCanvasElement]

  private var parentWidget: Widget = _

  override def parent: Widget = parentWidget

  override def parent_=(parentWidget: Widget): Unit = {
    this.parentWidget = parentWidget
    element.width = initWidth.getOrElse(parentWidget.element.clientWidth)
    element.height = initHeight.getOrElse(parentWidget.element.clientHeight)
  }

  def withContext(function: (CanvasRenderingContext2D) => Unit): Unit = function(element.getContext("2d").asInstanceOf[CanvasRenderingContext2D])
}


class CanvasSquareGrid(rows: Int, columns: Int) extends CanvasWidget with SquareGrid {

  private val SQUARE_SIZE = 5
  private val context = element.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def updateColor(row: Int, column: Int, newColor: Color): Unit = {
    withContext {context =>
      context.fillStyle = newColor
      context.fillRect(row * SQUARE_SIZE, column * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE)
    }
  }
}
