package blackjack

import blackjack.controller.BlackJackGame
import blackjack.view.OutputView

fun main() {
    try {
        BlackJackGame.start()
    } catch (e: Exception) {
        OutputView.printErrorMessage("[FATAL]: ${e.message ?: "[FATAL]: Unknown error."}")
    }
}
