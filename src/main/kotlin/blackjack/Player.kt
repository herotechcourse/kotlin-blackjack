package blackjack

class Player(val gamblerInfo: GamblerInfo) {
    private val _cards: MutableList<Card> = mutableListOf()
    var score: Int = 0
        private set
    val cards = _cards.toList()

    fun addCard(cards: List<Card>) {
        _cards.addAll(cards)
        updateScore()
    }

    fun getCards(): List<Card> {
        return _cards.toList()
    }

    private fun updateScore() {
        var aceCount = _cards.count { it -> it.rank == Rank.ACE }
        var totalScore = _cards.sumOf { it -> it.rank.value }
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
