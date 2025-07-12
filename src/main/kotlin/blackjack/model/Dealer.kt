package blackjack.model

import blackjack.controller.Deck

data class Dealer(override val name: String = "Dealer") : Playable() {
    fun shouldDrawCardOrNot(): Boolean {
        val score = calculateHand()
        return score <= 16
    }

    fun selfDrawInitCards(deck: Deck) {
        repeat(2) {
            drawCard(deck.drawCard())
        }
    }

    fun giveInitCardsToPlayers(
        players: List<Player>,
        deck: Deck,
    ) {
        repeat(2) {
            players.forEach { player ->
                player.drawCard(deck.drawCard())
            }
        }
    }
}
