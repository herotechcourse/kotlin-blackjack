package blackjack

import blackjack.enum.CardNumber
import blackjack.enum.CardSuit

class Deck(val cards: MutableList<Card>) {
    fun drawCard(): Card {
        return cards.removeFirst()
    }

    companion object {
        fun generate(shuffled: Boolean): Deck {
            val cardSuits = listOf(CardSuit.HEART, CardSuit.DIAMOND, CardSuit.CLUB, CardSuit.SPADE)
            var cards = cardSuits.flatMap { suit -> generateCardsWithSuit(suit) }
            if (shuffled) {
                cards = cards.shuffled()
            }
            return Deck(cards.toMutableList())
        }

        private fun generateCardsWithSuit(suit: CardSuit): List<Card> {
            return CardNumber.entries.map { number -> Card(suit, number) }
        }
    }
}
