package pl.gdyk.game.life.ui

import org.scalajs.dom.HTMLElement


trait Panel extends Widget {

  def add(child: Widget) = {
    element.appendChild(child.element)
    child.parent= this
  }

}

object RootPanel {
  def apply(id: String): Panel = {
    val rootElement = org.scalajs.dom.document.getElementById(id)
    new Panel {
      def parent: Widget = null

      def parent_=(parentWidget: Widget): Unit = {}

      def element: HTMLElement = rootElement
    }
  }
}
