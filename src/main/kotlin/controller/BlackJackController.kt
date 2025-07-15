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
        displayInitializedGame(blackjack)
        playersTurn(blackjack)
        dealerTurn(blackjack)
        displayResults(blackjack)
    }

    private fun displayInitializedGame(blackJack: BlackJack) {
        output.displayInitialCards(blackJack.players, blackJack.dealer)
    }

    private fun playersTurn(blackJack: BlackJack) {
        blackJack.players.forEach { player ->
            blackJack.playerTurn(
                player = player,
                doAfter = { output.displayPlayerTurn(player) },
                { input.requestPlayerDecision(player.name) == "y" },
            )
        }
    }

    private fun dealerTurn(blackJack: BlackJack) {
        blackJack.dealerTurn(
            blackJack.deck,
            { output.displayDealerGame() },
        )
    }

    private fun displayResults(blackjack: BlackJack) {
        output.displayFinalCardsOnHand(blackjack.players, blackjack.dealer)
        output.displayResults(blackjack.result(), blackjack.players)
    }
}
