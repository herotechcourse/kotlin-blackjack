package controller

import model.Dealer
import model.Player
import model.Players
import service.ResultCalculator
import util.isYes
import view.InputView
import view.OutputView

object BlackJackController {
    fun playGame() {
        try {
            val players = Players(getPlayerNames().map { Player(it) })
            val dealer = Dealer()
            startGame(players, dealer)
            runTurns(players, dealer)
            showResults(players, dealer)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            playGame()
        }
    }

    private fun getPlayerNames(): List<String> {
        val players = InputView.requestPlayerNames()
        return players
    }

    private fun startGame(
        players: Players,
        dealer: Dealer,
    ) {
        dealer.giveInitialCardsToPlayers(players)
        dealer.drawInitialCards()
        OutputView.displayInitialCards(players, dealer)
    }

    private fun runTurns(
        allPlayers: Players,
        dealer: Dealer,
    ) {
        allPlayers.players.forEach { player ->
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
        allPlayers: Players,
        dealer: Dealer,
    ) {
        OutputView.displayFinalCardsOnHand(allPlayers, dealer)
        val result = ResultCalculator.getResult(
            allPlayers.players.map { it.getScore() },
            dealer.getScore(),
        )
        OutputView.displayResults(result, allPlayers)
    }
}
