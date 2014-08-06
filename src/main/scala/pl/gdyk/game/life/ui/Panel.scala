package pl.gdyk.game.life.ui

import org.scalajs.dom


trait Panel extends Widget {

  def add(child: Widget) = {
    element.appendChild(child.element)
    child.parent= this
  }
}

object RootPanel {

  private def forElement(initElement: dom.HTMLElement) = new Panel {
    override def parent: Widget = null

    override def parent_=(parentWidget: Widget): Unit = {}

    def element: dom.HTMLElement = initElement
  }

  def apply(id: String): Panel = forElement(dom.document.getElementById(id))

  def apply(): Panel = forElement(dom.document.getElementsByTagName("body")(0).asInstanceOf[dom.HTMLElement])
}
