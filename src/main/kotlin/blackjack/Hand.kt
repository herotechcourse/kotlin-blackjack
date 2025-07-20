package blackjack

class Hand() {
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun sumCards(): Int {
        val cardValues = cards.map { card ->
            when (card.number) {
                in FACE_CARD_NUMBERS -> FACE_CARD_VALUE
                else -> card.number
            }
        }.toMutableList()

        cardValues.replaceAll { if (it == ACE_LOW && cardValues.sum() + ACE_HIGH <= BLACKJACK_LIMIT) ACE_HIGH else it }

        return cardValues.sum()
    }

    fun checkWinOrLose(): String {
        val cardValues = cards.map { card -> card.number }
        val score = cardValues.sum()
        return when {
            score == 21 -> "Win"
            score > 21 -> "Lose"
            else -> "Stay"
        }
    }

    override fun toString(): String {
        val cards = cards.joinToString(",")
        return cards
    }

    companion object {
        private const val ACE_LOW = 1
        private const val ACE_HIGH = 11
        private const val BLACKJACK_LIMIT = 21
        private const val FACE_CARD_VALUE = 10
        private val FACE_CARD_NUMBERS = setOf(11, 12, 13)
    }
}