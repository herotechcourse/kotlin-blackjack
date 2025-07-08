package blackjack
// The deck represents a shuffled pile of 52 cards to draw from
class Deck {
    // Holds the list of cards (initially shuffled)
    private val cards: MutableList<Card> = createShuffledDeck()
    // Creates all 52 cards by combining each suit with each rank, and then shuffles them
    private fun createShuffledDeck(): MutableList<Card> {
        val allCards = mutableListOf<Card>()
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                allCards.add(Card(suit, rank))
            }
        }
        allCards.shuffle()
        return allCards
    }
    // Removes and returns the top card from the deck
    fun draw(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("No more cards in the deck.")
        }
        return cards.removeAt(0)
    }
}
