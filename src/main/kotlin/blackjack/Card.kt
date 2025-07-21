package blackjack

class Card(val suit: CardSuit, val number: Int) {
    fun numberToCardDeckElements(number: Int): String {
        return when (number) {
            1 -> "A"
            10 -> "J"
            11 -> "Q"
            12 -> "K"
            else -> number.toString()
        }
    }

    override fun toString(): String {
        val cardValue = numberToCardDeckElements(number)
        return "%s%s".format(cardValue, suit.value)
    }

}
