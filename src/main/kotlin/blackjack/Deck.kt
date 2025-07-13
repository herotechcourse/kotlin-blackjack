package blackjack

class Deck(cards: List<Card>) {
    val cards = cards.shuffled().toMutableList()

    fun drawCard(): Card {
        return cards.removeFirst()
    }

    companion object {
        val cardPositionValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

        fun generate(): Deck{
            val cardSuits = listOf(CardSuit.HEART, CardSuit.DIAMOND, CardSuit.CLUB, CardSuit.SPADE)
            val cards = cardSuits.map { suit -> generateCardsWithSuit(suit) }.flatten()
            return Deck(cards)
        }

        private fun generateCardsWithSuit(suit: CardSuit): List<Card> {
            val cards = cardPositionValues.map { cardValue -> Card(suit, cardValue) }
            return cards
        }
    }
}