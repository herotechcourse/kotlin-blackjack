package controller

import model.BlackJack
import view.InputView
import view.OutputView

class BlackJackController(
    private val input: InputView,
    private val output: OutputView,
) {
    fun run() {
        val blackjack = BlackJack(input.requestPlayerNames())
        blackjack.setPlayersBet(input::requestPlayersBet)
        displayInitializedGame(blackjack)
        blackjack.playersTurn(output::displayPlayerTurn, input::requestPlayerDecision)
        dealerTurn(blackjack)
        blackjack.calcEarning()
        displayResults(blackjack)
    }

    private fun displayInitializedGame(blackJack: BlackJack) {
        output.displayInitialCards(blackJack.players.value, blackJack.dealer)
    }

    private fun dealerTurn(blackJack: BlackJack) {
        blackJack.dealerTurn(
            blackJack.deck,
            { output.displayDealerGame() },
        )
    }

    private fun displayResults(blackjack: BlackJack) {
        output.displayFinalCardsOnHand(blackjack.players.value, blackjack.dealer)
        output.displayResults(blackjack.dealer, blackjack.players.value)
    }
}
