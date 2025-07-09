package blackjack.model

class GameManager(private val dealer: Player, private val players: List<Player>) {
    private val cardDeck = CardDeck()

    init {
        setUp()
    }

    private fun setUp() {
        players.forEach { cardDeck.hit(it, 2) }
        cardDeck.hit(dealer)
    }
}
