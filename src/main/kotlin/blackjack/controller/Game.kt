package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Game(
    private val deck: Deck,
    private val dealer: Dealer,
) {
    fun startGame() {
        val playersName = InputView.askPlayerNames()
        val players = playersName.map { Player(it) }
        dealCardsToDealer()
        dealCardsToPlayer(players)
        OutputView.displayInitialCards(dealer, players)
        askPlayersToDraw(players)
        dealerDraws()
        OutputView.displayCardsWithTotalValue(dealer, players)
        compareFinalCards(players)
        OutputView.displayFinalResults(dealer, players)
    }

    fun dealCardsToDealer() {
        dealer.drawCard(deck.draw(2))
    }

    fun dealCardsToPlayer(players: List<Player>) {
        players.forEach {
            it.drawCard(deck.draw(2))
        }
    }

    fun askPlayersToDraw(players: List<Player>) {
        players.forEach {
            do {
                val answer = InputView.askToDrawCard(it.name)
                if (answer) {
                    it.drawCard(deck.draw(1))
                    it.updateActiveStatus(it.calculateTotalValueOfCards())
                    OutputView.displayHandCards(it)
                }
            } while (answer && it.isActive)
        }
    }

    fun dealerDraws() {
        val mustDraw = dealer.mustDraw(dealer.calculateTotalValueOfCards())
        if (mustDraw) {
            OutputView.displayDealerDrawMessage()
            dealer.drawCard(deck.draw())
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
