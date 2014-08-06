package pl.gdyk.game.life.ui

import org.scalajs.dom.HTMLElement


trait Widget extends UIObject {

  private var parentWidget: Widget = _

  def parent: Widget = parentWidget

  def parent_= (parentWidget: Widget) = this.parentWidget = parentWidget

}

class ElementWidget(initElement: HTMLElement) extends Widget {

  def this(tagName: String) = this(org.scalajs.dom.document.createElement(tagName))

  val element = initElement
}
