package pl.gdyk.game.life.ui

import org.scalajs.dom.{CanvasRenderingContext2D, HTMLCanvasElement}
import org.scalajs.dom

trait SquareGrid extends UIObject {
  def updateColor(row: Int, column: Int, newColor: Color)
}

class CanvasWidget(initWidth: Option[Pixels], initHeight: Option[Pixels]) extends Widget {

  def this() = this(None, None)
  def this(width: Pixels, height: Pixels) = this(Some(width), Some(height))

  val element = dom.document.createElement("canvas").asInstanceOf[HTMLCanvasElement]

  private var parentWidget: Widget = _

  override def parent: Widget = parentWidget

  override def parent_=(parentWidget: Widget): Unit = {
    this.parentWidget = parentWidget
    element.width = initWidth.getOrElse(parentWidget.element.clientWidth)
    element.height = initHeight.getOrElse(parentWidget.element.clientHeight)
  }

  def actualWidth: Pixels = element.width
  def actualHeight: Pixels = element.height

  def withContext(function: (CanvasRenderingContext2D) => Unit): Unit = function(element.context2d)
}


class CanvasSquareGrid(rows: Int, columns: Int) extends CanvasWidget with SquareGrid {

  private def squareWidth: Pixels = actualWidth / columns
  private def squareHeight: Pixels = actualHeight / rows

  def updateColor(row: Int, column: Int, newColor: Color): Unit = {
    dom.window.console.log("actual width = " + actualWidth)
    withContext {context =>
      context.fillStyle = newColor
      context.fillRect(column * squareWidth, row * squareHeight, squareWidth, squareHeight)
    }
  }
}
