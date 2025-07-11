package blackjack.model

data class CardDeck(private val cards: Cards) {
    constructor() : this(initPokerCards())

    companion object {
        private fun initPokerCards() = Cards(list().shuffled().toSet())

        private fun list(): List<Card> {
            return Suit.entries.flatMap { suit -> Rank.entries.map { rank -> Card(suit, rank) } }
        }
    }

    fun numberOfCards() = cards.cards.size

    fun getCards() = cards.cards.toList()

    fun hit(
        player: Player,
        repeat: Int = 1,
    ) {
        repeat(repeat) { cards.moveCard(player) }
    }
}
