package blackjack.model

class PlayingCard private constructor(val rank: Rank, val suit: Suit) {
    val value: Int get() = rank.value

    constructor(form: String) : this(Rank.of(form.substring(0, 1)), Suit.with(form.substring(1, 2)))

    companion object {
        val deck = Deck(generateAllDeck())

        private fun generateDeckOfSuit(suit: Suit): List<PlayingCard> {
            return Rank.entries.map { PlayingCard(it, suit) }
        }

        fun generateAllDeck(): List<PlayingCard> {
            return Suit.entries.map { generateDeckOfSuit(it) }.flatten()
        }

        fun of(
            rank: Rank,
            suit: Suit,
        ): PlayingCard {
            return deck.cards.firstOrNull { it.rank == rank && it.suit == suit }
                ?: throw IllegalArgumentException("Unknown card with $rank and $suit")
        }
    }
}
