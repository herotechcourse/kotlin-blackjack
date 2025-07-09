package blackjack.model

class Deck {
    private val cards: MutableList<Card> =
        Suit
            .entries
            .flatMap { suit ->
                Rank.entries.map { rank -> Card(rank, suit) }
            }.toMutableList()

    fun getCards(): List<Card> {
        return cards
    }

    fun shuffleCards() = cards.shuffled()

    fun giveCard(): Card {
        require(cards.isNotEmpty()) { "Deck is empty!" }
        return cards.removeAt(0)
    }
}
