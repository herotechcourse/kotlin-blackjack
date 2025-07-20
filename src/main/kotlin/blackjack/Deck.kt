package blackjack

class Deck(val cards: MutableList<Card>) {
    fun drawCard(): Card {
        return cards.removeFirst()
    }

    companion object {
        val cardPositionValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

        fun generate(shuffled: Boolean): Deck{
            val cardSuits = listOf(CardSuit.HEART, CardSuit.DIAMOND, CardSuit.CLUB, CardSuit.SPADE)
            var cards = cardSuits.flatMap { suit -> generateCardsWithSuit(suit) }
            if (shuffled){
                cards = cards.shuffled().toMutableList()
            }
            return Deck(cards.toMutableList())
        }

        private fun generateCardsWithSuit(suit: CardSuit): List<Card> {
            val cards = cardPositionValues.map { cardValue -> Card(suit, cardValue) }
            return cards
        }
    }
}
