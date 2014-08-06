package pl.gdyk.game.life.ui.responsive

import pl.gdyk.game.life.ui.{ElementWidget, Widget, Panel}
import org.scalajs.dom
import org.scalajs.dom.HTMLElement

class ResponsiveGrid() extends ElementWidget("div") with Panel {

  element.className = "container"

  def addRow(row: ResponsiveGridRow) = {
    element.appendChild(row.element)
    row.parent = this
  }
}

class ResponsiveGridRow(columnCount: Int) extends ElementWidget("div") with Panel {

  def addColumn(row: ResponsiveGridCell): Unit = {
    element.appendChild(row.element)
    row.parent = this
  }

  element.className = "row"
}

class ResponsiveGridCell(widthInColumns: Int, content: Option[Widget]) extends ElementWidget("div") with Panel {

  def this(widthInColumns: Int) = this(widthInColumns, None)
  def this(widthInColumns: Int, content: Widget) = this(widthInColumns, Some(content))

  element.className = "col-md-" + widthInColumns

  override def parent_=(widget: Widget) = {
    super.parent_=(widget)
    content.foreach(add)
  }
}
