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
}