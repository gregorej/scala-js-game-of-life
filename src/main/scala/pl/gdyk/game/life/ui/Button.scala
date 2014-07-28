package pl.gdyk.game.life.ui

import org.scalajs.dom.HTMLElement
import org.scalajs.dom


class Button(label: String) extends Widget {

  private var parentWidget: Widget = _


  def parent: Widget = parentWidget

  def parent_=(parentWidget: Widget): Unit = this.parentWidget = parentWidget

  val element: HTMLElement = dom.document.createElement("button")

  element.innerHTML = label
  element.setAttribute("type", "button")
}
