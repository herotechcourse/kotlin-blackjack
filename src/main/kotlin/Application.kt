import controller.BlackJackController
import view.InputView
import view.OutputView

fun main() {
    BlackJackController(InputView(), OutputView()).run()
}
