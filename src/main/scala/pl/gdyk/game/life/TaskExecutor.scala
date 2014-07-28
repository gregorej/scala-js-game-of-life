package pl.gdyk.game.life


trait Task {
  def cancel(): Unit
}

trait TaskExecutor {
  def execute(task: () => Any, milliseconds: Int): Task
}
