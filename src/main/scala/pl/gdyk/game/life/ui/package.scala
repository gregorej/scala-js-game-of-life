package pl.gdyk.game.life

import org.scalajs.dom.{HTMLCanvasElement, CanvasRenderingContext2D}


package object ui {

  type Color = String

  type Pixels = Int

  implicit class EnrichedInt(integer: Int) {
    def px: Pixels = integer
  }

  implicit class EnrichedHTMLCanvasElement(canvas: HTMLCanvasElement) {
    def context2d: CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
  }

  implicit class EnrichedCanvasRenderingContext2d(ctx: CanvasRenderingContext2D) {

    def fillColor_=(color: Color) = ctx.fillStyle = color

    def fillColor: Color = ctx.fillStyle.toString

    def strokeColor_=(color: Color) = ctx.strokeStyle = color

    def strokeColor: Color = ctx.strokeStyle.toString
  }

  object Color {
    val WHITE: Color = fromHex("#FFFFFF")
    val BLACK: Color = fromHex("#000000")
    val GREEN: Color = fromHex("#00FF00")
    val RED: Color = fromHex("#FF0000")
    val BLUE: Color = fromHex("#0000FF")

    implicit def fromHex(hex: String): Color = hex

  }


}
