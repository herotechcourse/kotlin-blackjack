package blackjack

class PlayerCards(val cards: MutableList<Card> = mutableListOf()) {
    var score: Int = 0
        private set

    fun addCard(element: Card) {
        cards.add(element)
        updateScore()
    }

    private fun updateScore() {
        var aceCount = cards.count { it -> it.rank == Rank.ACE }
        var totalScore = cards.sumOf { it -> it.rank.value }
        while (totalScore > WINNING_SCORE && aceCount > 0) {
            totalScore -= 10
            aceCount--
        }
        score = totalScore
    }

    companion object {
        private const val WINNING_SCORE = 21
    }
}
