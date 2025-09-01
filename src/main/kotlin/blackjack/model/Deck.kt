package blackjack.model

import blackjack.enum.CardNumber
import blackjack.enum.CardSuit

class Deck(val cards: MutableList<Card>) {
    fun drawCard(): Card {
        return cards.removeFirst()
    }

    companion object {
        val cardNumbers =
            listOf(
                CardNumber.ACE,
                CardNumber.TWO,
                CardNumber.THREE,
                CardNumber.FOUR,
                CardNumber.FIVE,
                CardNumber.SIX,
                CardNumber.SEVEN,
                CardNumber.EIGHT,
                CardNumber.NINE,
                CardNumber.TEN,
                CardNumber.JACK,
                CardNumber.QUEEN,
                CardNumber.KING,
            )

        fun generate(shuffled: Boolean): Deck {
            val cardSuits = listOf(CardSuit.HEART, CardSuit.DIAMOND, CardSuit.CLUB, CardSuit.SPADE)
            var cards = cardSuits.flatMap { suit -> generateCardsWithSuit(suit) }
            if (shuffled) {
                cards = cards.shuffled().toMutableList()
            }
            return Deck(cards.toMutableList())
        }

        private fun generateCardsWithSuit(suit: CardSuit): List<Card> {
            val cards = cardNumbers.map { cardValue -> Card(suit, cardValue) }
            return cards
        }
    }
}
