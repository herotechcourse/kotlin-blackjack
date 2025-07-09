package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

object Controller {

    fun run() {
        try {
            val players = processPlayerNames().map { it ->
                Player(it)
            }
            val dealer = Player(GamblerInfo("dealer"))
            OutputView.displayNamesOfPlayers(players)
            val deck = Deck()
        } catch (err: IllegalArgumentException) {
            OutputView.displayErrorMessages(err.message)
        }
    }

    private fun startGame() {
        processPlayerNames()
    }


    fun processPlayerNames(): List<GamblerInfo> {
        repeat(MAX_ATTEMPTS) {
            try {
                val names = InputView.getNamesOfPlayers()
                return names
                    .split(",")
                    .map(String::trim)
                    .map { it -> GamblerInfo(it) } //to avoid abundant function: caller
            } catch (err: IllegalArgumentException) {
                OutputView.displayErrorMessages(err.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }


    //fun askWhetherToDrawOrNot() {
//            getUserInputAsString()
    // }

    private const val MAX_ATTEMPTS = 5
    private const val MAX_ATTEMPT_MESSAGE = "Too many attempts"
}
