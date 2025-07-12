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
        dealer.selfDrawInitialCards()
        dealer.dealInitialCardsToPlayers(players)
        OutputView.displayInitialCards(dealer, players)
        askPlayersToDraw(players)
        dealerDraws()
        OutputView.displayCardsWithTotalValue(dealer)
        players.forEach { player -> OutputView.displayCardsWithTotalValue(player) }
        compareFinalCards(players)
        OutputView.displayFinalResults(dealer, players)
    }

    fun askPlayersToDraw(players: List<Player>) {
        players.forEach {
            do {
                val answer = InputView.askToDrawCard(it.name)
                if (answer) {
                    dealer.dealCardToPlayer(it)
                    it.updateActiveStatus(it.calculateTotalValueOfCards())
                    println(OutputView.showHandCards(it, false))
                }
            } while (answer && it.isActive)
        }
    }

    fun dealerDraws() {
        val mustDraw = dealer.mustDraw(dealer.calculateTotalValueOfCards())
        if (mustDraw) {
            OutputView.displayDealerDrawMessage(dealer)
            dealer.selfDrawCard()
        }
    }

    fun compareFinalCards(players: List<Player>) {
        players.forEach {
            if (it.calculateTotalValueOfCards() < dealer.calculateTotalValueOfCards()) {
                it.isActive = false
            }
        }
    }
}
