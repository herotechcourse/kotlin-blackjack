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
        askPlayersToHit(players)
        dealerDraws()
        OutputView.displayCardsWithTotalValue(dealer)
        players.forEach { player -> OutputView.displayCardsWithTotalValue(player) }
        compareFinalCards(players)
        OutputView.displayFinalResults(dealer, players)
    }

    fun askPlayersToHit(players: List<Player>) {
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

    fun dealerDraws() {
        val mustDraw = dealer.mustDraw(dealer.cardsInHand.calculateTotalValueOfCards())
        if (mustDraw) {
            OutputView.displayDealerDrawMessage(dealer)
            dealer.selfDrawCard()
        }
    }

    fun compareFinalCards(players: List<Player>) {
        players.forEach {
            if (it.cardsInHand.calculateTotalValueOfCards() < dealer.cardsInHand.calculateTotalValueOfCards()) {
                it.isBusted = true
            }
        }
    }
}
