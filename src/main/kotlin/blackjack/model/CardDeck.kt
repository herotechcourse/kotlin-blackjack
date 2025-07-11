package blackjack.model

data class CardDeck(private val hold: Hold) {
    constructor() : this(initPokerCards())

    companion object {
        private fun initPokerCards() = Hold(list().shuffled().toSet())

        private fun list(): List<Card> {
            return Suit.entries.flatMap { suit -> Rank.entries.map { rank -> Card(suit, rank) } }
        }
    }

    fun numberOfCards() = hold.cards.size

    fun getCards() = hold.cards.toList()

    fun hit(
        player: Player,
        repeat: Int = 1,
    ) {
        repeat(repeat) { hold.moveCard(player) }
    }
}
