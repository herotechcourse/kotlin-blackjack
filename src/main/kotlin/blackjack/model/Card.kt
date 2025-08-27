package blackjack.model

import blackjack.enum.CardNumber
import blackjack.enum.CardSuit

class Card(val suit: CardSuit, val number: CardNumber) {
    fun numberToCardDeckElements(number: CardNumber): String {
        return when (number) {
            CardNumber.ACE -> "A"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
            else -> number.toString()
        }
    }
}
