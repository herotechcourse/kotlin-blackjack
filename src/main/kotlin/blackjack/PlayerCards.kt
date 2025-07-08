package blackjack

class PlayerCards(val cards: MutableList<Card> = mutableListOf()) {
    var score: Int = 0
        private set

    fun addCard(element: Card) {
        updateScore(element.rank)
        cards.add(element)
    }

    private fun updateScore(rank: Rank) {
        if (rank == Rank.ACE && (score + Rank.ACE.value) > WINNING_SCORE) {
            score += 1
        } else {
            score += rank.value
        }
    }

    companion object {
        private const val WINNING_SCORE = 21
    }
}
