package blackjack.model

class GameManager(private val dealer: Player, private val players: List<Player>) {
    private val cardDeck = CardDeck()

    init {
        setUp()
    }

    private fun setUp() {
        println("something")
    }
}
