package controller

import model.BlackJack
import model.Dealer
import model.Player
import service.ResultCalculator
import view.InputView
import view.OutputView

class BlackJackController {
    private val blackJack = BlackJack(InputView.requestPlayerNames())

    fun runGame() {
        displayInitializedGame()
        playersTurn(blackJack.players, blackJack.dealer)
        dealersTurn(blackJack.dealer)
        displayResults(blackJack.players, blackJack.dealer)
    }

    private fun displayInitializedGame() {
        OutputView.displayInitialCards(blackJack.players, blackJack.dealer)
    }

    private fun playersTurn(
        players: List<Player>,
        dealer: Dealer,
    ) {
        players.forEach { player ->
            try {
                while (player.makeDecision(InputView.requestPlayerDecision(player.name).single().code)) {
                    player.drawCard(dealer.dealCard())
                    OutputView.displayPlayersTurn(player)
                }
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun dealersTurn(dealer: Dealer) {
        while (dealer.makeDecision(dealer.getScore())) {
            dealer.drawCard(dealer.dealCard())
            OutputView.displayDealersGame()
        }
    }

    private fun displayResults(
        players: List<Player>,
        dealer: Dealer,
    ) {
        OutputView.displayFinalCardsOnHand(players, dealer)
        OutputView.displayResults(
            ResultCalculator.getResult(
                players.map { it.getScore() },
                dealer.getScore(),
            ),
            players,
        )
    }
}
