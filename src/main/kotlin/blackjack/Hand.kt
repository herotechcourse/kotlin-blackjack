package blackjack

import blackjack.enum.CardNumber

class Hand(val cards: MutableList<Card> = mutableListOf()) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun sumCards(): Int {
        var total = cards.sumOf { it.number.value }
        val hasAce = cards.any { it.number == CardNumber.ACE }

        if (hasAce && total + 10 <= BLACKJACK_LIMIT) {
            total += 10
        }

        return total
    }

    fun determineResult(
        playerScore: Int,
        dealerScore: Int,
    ): String {
        return when {
            playerScore > BLACKJACK_LIMIT -> "Lose"
            dealerScore > BLACKJACK_LIMIT -> "Win"
            playerScore > dealerScore -> "Win"
            playerScore < dealerScore -> "Lose"
            else -> "Push"
        }
    }

    companion object {
        private const val BLACKJACK_LIMIT = 21
    }
}
