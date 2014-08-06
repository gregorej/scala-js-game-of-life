package pl.gdyk.game.life

import org.scalajs.dom.CanvasRenderingContext2D


package object ui {

  type Color = String

  implicit class EnrichedCanvasRenderingContext2d(ctx: CanvasRenderingContext2D) {

    def fillColor_=(color: Color) = ctx.fillStyle = color

    def fillColor: Color = ctx.fillStyle.toString

    def strokeColor_=(color: Color) = ctx.strokeStyle = color

    def strokeColor: Color = ctx.strokeStyle.toString
  }

  object Color {

    val GREEEN: Color = "#00FF00"
    val RED: Color = "#FF0000"
    val BLUE: Color = "#0000FF"

    implicit def forHex(hex: String): Color = hex

  }


}
