package blackjack.model

class Participants(private val players: Players, private val dealer: Dealer) {
    fun all(): List<Participant> = listOf(dealer) + players.toList()

    fun dealInitialCards(deck: Deck) {
        all().forEach {
            it.drawCard(deck.draw(2))
        }
    }

    fun dealerTurn(deck: Deck) {
        while (dealer.mustDraw()) {
            dealer.drawCard(deck.draw())
        }
    }
}
