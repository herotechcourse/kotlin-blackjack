package blackjack

class Deck(val cards: List<Card>) {

    companion object {
        val cardValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

        fun generate(): Deck{
            val cardSuits = listOf(CardSuit.HEART, CardSuit.DIAMOND, CardSuit.CLUB, CardSuit.SPADE)
            val cards = cardSuits.map { suit -> generateCardsWithSuit(suit) }.flatten()
            return Deck(cards)
        }

        private fun generateCardsWithSuit(suit: CardSuit): List<Card> {
            val cards = cardValues.map { cardValue -> Card(suit, cardValue) }
            return cards
        }
    }
}