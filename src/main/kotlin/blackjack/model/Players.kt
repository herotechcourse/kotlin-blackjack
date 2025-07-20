package blackjack.model

class Players(private val players: List<Player>) : Iterable<Player> {
    override fun iterator(): Iterator<Player> = players.iterator()

    fun dealInitialCardsToAll(deck: Deck) {
        players.forEach { player ->
            player.dealInitialCards(deck)
        }
    }

    fun toList(): List<Player> = players
}
