package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    private var players: List<Player> = mutableListOf()
    private val dealer: Player = Player(GamblerInfo("dealer"))
    private val deck = Deck()

    fun run() {
        try {
            createPlayers()
            OutputView.displayNamesOfPlayers(players)
            roundOne()
            OutputView.displayCardsOfDealer(dealer)
            players.forEach { OutputView.displayCardsOfPlayers(it) }
        } catch (err: IllegalArgumentException) {
            OutputView.displayErrorMessages(err.message)
        }
    }

    private fun createPlayers() {
        players =
            processPlayerNames().map { Player(it) }
    }

    private fun roundOne() {
        dealer.addCard(deck.drawCard(2))
        players.forEach { it.addCard(deck.drawCard(2)) }
    }

    fun processPlayerNames(): List<GamblerInfo> {
        repeat(MAX_ATTEMPTS) {
            try {
                val names = InputView.getNamesOfPlayers()
                return names
                    .split(",")
                    .map(String::trim)
                    .map { it -> GamblerInfo(it) } // to avoid abundant function: caller
            } catch (err: IllegalArgumentException) {
                OutputView.displayErrorMessages(err.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    // fun askWhetherToDrawOrNot() {
//            getUserInputAsString()
    // }
    companion object {
        private const val MAX_ATTEMPTS = 5
        private const val MAX_ATTEMPT_MESSAGE = "Too many attempts"
    }
}
