package pl.gdyk.game.life.ui

import org.scalajs.dom

import pl.gdyk.game.life.{Task, TaskExecutor}

object JavascriptTaskExecutor extends TaskExecutor {
  def execute(task: () => Any, milliseconds: Int): Task = {
    val handler = dom.window.setInterval(() => task(), milliseconds)
    new Task {
      def cancel(): Unit = dom.window.clearInterval(handler)
    }
  }
}
