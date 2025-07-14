package blackjack

class Card(val suit: CardSuit, val number: Int) {
    fun getPossibleValues(): List<Int> {
        when (number) {
            1 -> listOf(1, 11)
            10, 11, 12 -> listOf(10)
            else -> listOf(number)
        }
        return listOf(number)
    }

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