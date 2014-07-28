package pl.gdyk.game.life.ui


trait Widget extends UIObject {

  def parent: Widget

  def parent_= (parentWidget: Widget)

}
