package controller

import model.Dealer
import model.Player
import service.ResultCalculator
import util.isYes
import view.InputView
import view.OutputView

object BlackJackController {
    fun playGame() {
        val players = getPlayerNames().map { Player(it) }
        val dealer = Dealer()
        startGame(players, dealer)
        runTurns(players, dealer)
        showResults(players, dealer)
    }

    private fun getPlayerNames(): List<String> {
        val players = InputView.requestPlayerNames()
        require(players.size <= 6) { "Maximum player names must be 6" }
        return players
    }

    private fun startGame(
        players: List<Player>,
        dealer: Dealer,
    ) {
        dealer.giveInitialCardsToPlayers(players)
        dealer.drawInitialCards()
        OutputView.displayInitialCards(players, dealer)
    }

    private fun runTurns(
        players: List<Player>,
        dealer: Dealer,
    ) {
        players.forEach { player ->
            while (InputView.requestPlayerDecision(player.name).isYes()) {
                player.requestCardFromDealer(dealer)
                OutputView.displayPlayersTurn(player)
            }
        }
        while (dealer.makeDecision()) {
            dealer.dealFromSelf()
            OutputView.displayDealersGame()
        }
    }

    private fun showResults(
        players: List<Player>,
        dealer: Dealer,
    ) {
        OutputView.displayFinalCardsOnHand(players, dealer)
        val result =
            ResultCalculator.getResult(
                players.map { it.getScore() },
                dealer.getScore(),
            )
        OutputView.displayResults(result, players)
    }
}
