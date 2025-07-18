package blackjack.game

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Player

class BlackJackGame(
    private val dealer: Dealer,
    private val players: List<Player>,
    private val getAnswer: (Player) -> Boolean,
    private val displayPlayerHand: (Player) -> Unit,
) {
    private val deck = Deck()

    init {
        dealInitialCards()
    }

    private fun dealInitialCards() {
        players.forEach { it.dealInitialCards(deck) }
        dealer.dealInitialCards(deck)
    }

    fun start() {
        players.forEach { player ->
            while (player.handState.isRunning() && getAnswer(player)) {
                player.draw(deck.drawCard())
                displayPlayerHand(player)
            }
            if (player.handState.isRunning()) player.stay()
        }
        dealer.playTurn(deck)
    }
}
