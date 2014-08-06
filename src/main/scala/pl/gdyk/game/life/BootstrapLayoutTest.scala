package pl.gdyk.game.life

import scala.scalajs.js
import pl.gdyk.game.life.ui._
import pl.gdyk.game.life.ui.responsive.{ResponsiveGrid, ResponsiveGridCell, ResponsiveGridRow}

@js.annotation.JSExport(name = "BootstrapLayoutTest")
object BootstrapLayoutTest {
  val canvas = new CanvasWidget(500, 500)

  @js.annotation.JSExport
  def main()  = {

    buildLayout()
    canvas.withContext{ ctx =>
      ctx.fillColor =Color.GREEEN
      ctx.fillRect(0, 0, 500, 500)
    }

  }
  private def buildLayout() {
    val grid = new ResponsiveGrid()
    val row = new ResponsiveGridRow(12)
    row.addColumn(new ResponsiveGridCell(3, new TextBlock("column 3")))
    row.addColumn(new ResponsiveGridCell(9, canvas))
    grid.addRow(row)

    val root = RootPanel("main")
    root.add(grid)
  }
}
