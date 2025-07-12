package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Game(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val deck: Deck,
    private val dealer: Dealer,
) {
    fun startGame() {
        val playersName = inputView.askPlayerNames()
        val players = playersName.map { Player(it) }
        dealCardsToDealer()
        dealCardsToPlayer(players)
        outputView.displayInitialCards(dealer, players)
        askPlayersToDraw(players)
        dealerDraws()
        outputView.displayCardsWithTotalValue(dealer)
        players.forEach { player ->  outputView.displayCardsWithTotalValue(player)}
        compareFinalCards(players)
        outputView.displayFinalResults(dealer, players)
    }

    fun dealCardsToDealer() {
        repeat(2) {
            dealer.drawCard(deck.giveCard())
        }
    }

    fun dealCardsToPlayer(players: List<Player>) {
        repeat(2) {
            players.forEach {
                it.drawCard(deck.giveCard())
            }
        }
    }

    fun askPlayersToDraw(players: List<Player>) {
        players.forEach {
            do {
                val answer = inputView.askToDrawCard(it.name)
                if (answer) {
                    it.drawCard(deck.giveCard())
                    it.updateActiveStatus(it.calculateTotalValueOfCards())
                    println(outputView.showHandCards(it, false))
                }
            } while (answer && it.isActive)
        }
    }

    fun dealerDraws() {
        val mustDraw = dealer.mustDraw(dealer.calculateTotalValueOfCards())
        if (mustDraw) {
            outputView.displayDealerDrawMessage()
            dealer.drawCard(deck.giveCard())
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
