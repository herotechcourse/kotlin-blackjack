package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Game(
    private val dealer: Dealer,
) {
    fun startGame() {
        val playersName = InputView.askPlayerNames()
        val players = playersName.map { Player(it) }

        assignInitialCards(players)

        hitOrStay(players)

        compareFinalCards(players)
    }

    private fun assignInitialCards(players: List<Player>) {
        dealer.selfDrawInitialCards()
        dealer.dealInitialCardsToPlayers(players)
        OutputView.displayInitialCards(dealer, players)
    }

    private fun hitOrStay(players: List<Player>) {
        askPlayersToHit(players)
        dealerDraws()
        OutputView.displayCardsWithTotalValue(dealer)
        players.forEach { player -> OutputView.displayCardsWithTotalValue(player) }
    }

    private fun askPlayersToHit(players: List<Player>) {
        players.forEach {
            do {
                val answer = InputView.askToHit(it.name)
                if (answer) {
                    dealer.dealCardToPlayer(it)
                    it.updateBustedStatus(it.cardsInHand.calculateTotalValueOfCards())
                    println(OutputView.showHandCards(it, false))
                }
            } while (answer && !it.isBusted)
        }
    }

    private fun dealerDraws() {
        var mustDraw = dealer.mustDraw(dealer.cardsInHand.calculateTotalValueOfCards())
        while (mustDraw) {
            OutputView.displayDealerDrawMessage(dealer)
            dealer.selfDrawCard()
            mustDraw = dealer.mustDraw(dealer.cardsInHand.calculateTotalValueOfCards())
        }
    }

    private fun compareFinalCards(players: List<Player>) {
        val dealerPoints = dealer.cardsInHand.calculateTotalValueOfCards()
        players.forEach {
            it.comparePointsAgainstDealer(dealerPoints)
        }
        OutputView.displayFinalResults(dealer, players)
    }
}
