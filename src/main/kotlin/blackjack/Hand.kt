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

    fun determineResult(playerScore: Int, dealerScore: Int): String {
        return when {
            playerScore > BLACKJACK_LIMIT -> "Lose"
            dealerScore > BLACKJACK_LIMIT -> "Win"
            playerScore > dealerScore -> "Win"
            playerScore < dealerScore -> "Lose"
            else -> "Push"
        }
    }

    companion object {
        private const val ACE_LOW = 1
        private const val ACE_HIGH = 11
        private const val BLACKJACK_LIMIT = 21
        private const val FACE_CARD_VALUE = 10
        private val FACE_CARD_NUMBERS = setOf(11, 12, 13)
    }
}
