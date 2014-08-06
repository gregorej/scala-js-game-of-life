package pl.gdyk.game.life.ui


class Button(label: String) extends ElementWidget("button") {

  element.innerHTML = label
  element.setAttribute("type", "button")
}
